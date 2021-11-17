package com.example.stars.di

import com.example.stars.network.RetrofitService
import com.example.stars.repository.SignRepository
import com.example.stars.repository.SignRepositoryImpl
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    private val BASE_URL = "https://aztro.sameerkumar.website"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .baseUrl(BASE_URL)
        .build()

    @Singleton
    @Provides
    fun provideRetrofitService(retrofit: Retrofit): RetrofitService = retrofit.create(RetrofitService::class.java)

    @Singleton
    @Provides
    fun provideSignRepository(retrofitService: RetrofitService): SignRepository {
        return SignRepositoryImpl(retrofitService)
    }
}