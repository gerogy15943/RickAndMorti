package com.example.rickandmorti.interfaces

import com.example.rickandmorti.models.CharactersResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HeroService {
    @GET("./character")
    suspend fun getAllCharacters(@Query("page") page: Int): Response<CharactersResponse>

}