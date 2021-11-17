package com.example.stars.network

import com.example.stars.model.Sign
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitService {

    @Headers("Accept: application/json")
    @POST(".")
    suspend fun getSignAndDay(@Query("sign") sign: String, @Query("day") day: String): Response<Sign>
}