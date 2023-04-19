package com.example.paginationexample.API

import com.example.paginationexample.models.Passengers
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v1/passenger")
    suspend fun getPassengers(@Query("page") page: Int,@Query("size") size: Int = 10): Passengers
}