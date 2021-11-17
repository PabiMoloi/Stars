package com.example.stars.repository

import com.example.stars.model.Sign
import com.example.stars.network.RetrofitService
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class SignRepositoryImpl @Inject constructor(private val retrofitService: RetrofitService) : SignRepository {

    override suspend fun getSignAndDay(sign: String, day: String): Response<Sign>
    {
        return retrofitService.getSignAndDay(sign, day)
    }
}