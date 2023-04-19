package com.example.paginationexample

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paginationexample.API.ApiService
import com.example.paginationexample.models.Data

class PassengersPagingSource(private val passengerApi: ApiService): PagingSource<Int, Data>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        try {
            val position = params.key ?: 0
            val response = passengerApi.getPassengers(position)
            return LoadResult.Page(
                data = response.data,
                prevKey = if(position == 0) null else position-1,
                nextKey = if(position == response.totalPages) null else position+1
            )
        }catch (e:Exception){
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1) ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
//        if(state.anchorPosition != null){
//            val anchorPage = state.closestPageToPosition(state.anchorPosition!!)
//            if (anchorPage?.prevKey != null){
//                return anchorPage.prevKey!!.plus(1)
//            }
//            else if(anchorPage?.nextKey != null){
//                return anchorPage.nextKey!!.minus(1)
//            }
//        }                    
//        return null

    }
}