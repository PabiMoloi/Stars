package com.example.stars.viewmodel

import android.util.Log
import android.util.Log.DEBUG
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stars.model.Sign
import com.example.stars.repository.SignRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignViewModel @Inject constructor(private val repository: SignRepository) : ViewModel() {

    private val _signInfo = MutableLiveData<Sign>()
    val signInfo: LiveData<Sign> = _signInfo
    val isLoading = MutableLiveData<Boolean>()

    fun retrieveSignInfo(sign: String, day: String) {
        isLoading.value = true
        viewModelScope.launch {
            val response = repository.getSignAndDay(sign, day)
            if (response.isSuccessful) {
                isLoading.value = false
                _signInfo.value = response.body()
            } else {
                Log.v("error: ", response.errorBody().toString())
            }
        }
    }
}