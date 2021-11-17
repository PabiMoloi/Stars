package com.example.stars.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stars.model.SignDesc
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SignSelectionViewModel @Inject constructor(private val application: Application) :
    ViewModel() {

    private val _signs = MutableLiveData<List<SignDesc>>()
    val signs: LiveData<List<SignDesc>> = _signs

    fun getSigns() {
        viewModelScope.launch {
            val jsonFileString = getDataFromAsset()
            jsonFileString?.let { Log.i("data", it) }

            val gson = Gson()
            val listSignType = object : TypeToken<List<SignDesc>>() {}.type

            val persons: List<SignDesc> = gson.fromJson(jsonFileString, listSignType)
            _signs.value = persons
        }
    }

    private fun getDataFromAsset(): String? {
        val jsonString: String
        try {
            jsonString = application.applicationContext.assets.open("Signs.json").bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
}