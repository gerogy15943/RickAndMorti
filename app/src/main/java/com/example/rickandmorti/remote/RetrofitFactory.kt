package com.example.rickandmorti.remote

import com.example.rickandmorti.interfaces.HeroService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
        val base_url = "https://rickandmortyapi.com/api/"

        private fun getOkHttpClient(): OkHttpClient{
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            return OkHttpClient.Builder()
             .addInterceptor(loggingInterceptor)
             .build()
    }

        private fun getRetrofitClient(): Retrofit {
            val contentType = "application/json".toMediaType()
            return Retrofit.Builder()
                .baseUrl(base_url)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
        }

        fun getHeroesService(): HeroService = getRetrofitClient().create(HeroService::class.java)
}