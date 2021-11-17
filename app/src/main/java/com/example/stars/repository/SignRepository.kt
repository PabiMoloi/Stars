package com.example.stars.repository

import com.example.stars.model.Sign
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface SignRepository {
    suspend fun getSignAndDay(sign: String, day: String): Response<Sign>
}