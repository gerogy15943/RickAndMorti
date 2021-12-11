package com.example.rickandmorti.repositories

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmorti.interfaces.HeroService
import com.example.rickandmorti.models.Characters
import com.example.rickandmorti.remote.RetrofitFactory
import kotlinx.coroutines.flow.Flow

object HeroRepository {

    fun getCharacters(): Flow<PagingData<Characters>> {
        return  Pager(
            PagingConfig(pageSize = 25),
            pagingSourceFactory = { HeroPagingDataSource(RetrofitFactory.getHeroesService()) }
        ).flow
    }
}