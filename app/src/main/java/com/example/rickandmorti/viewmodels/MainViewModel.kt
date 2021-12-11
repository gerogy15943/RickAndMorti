package com.example.rickandmorti.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import androidx.paging.map
import com.example.rickandmorti.models.Characters
import com.example.rickandmorti.repositories.HeroRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.coroutines.CoroutineContext

class MainViewModel(): ViewModel() {

    fun getHeroes(): Flow<PagingData<Characters>>{
        return HeroRepository.getCharacters().map { it }

    }


}