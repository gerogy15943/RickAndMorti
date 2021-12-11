package com.example.rickandmorti.repositories

import android.net.Uri
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorti.interfaces.HeroService
import com.example.rickandmorti.models.Characters

class HeroPagingDataSource(private val service: HeroService): PagingSource<Int, Characters>() {
    override fun getRefreshKey(state: PagingState<Int, Characters>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Characters> {
        val pageNumber = params.key ?: 1
        return try{
            val response = service.getAllCharacters(pageNumber)
            val pagedResponse = response.body()
            val data = pagedResponse?.results

            var nextPage: Int? = null
            var prevPage: Int? = null
            if(pagedResponse?.pageInfo?.next != null) {
                var uri1 = Uri.parse(pagedResponse.pageInfo.next)
                val nextPageQuery = uri1.getQueryParameter("page")
                nextPage = nextPageQuery?.toInt()

                /*if (pagedResponse.pageInfo.prev != null) {
                    var uri2 = Uri.parse(pagedResponse.pageInfo.prev)
                    val prevPageQuery = uri2?.getQueryParameter("page")
                    prevPage = prevPageQuery?.toInt()
                }*/
            }

             LoadResult.Page(
                data = data.orEmpty(),
                prevKey = prevPage,
                nextKey = nextPage
            )

        } catch (e: Exception){
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}