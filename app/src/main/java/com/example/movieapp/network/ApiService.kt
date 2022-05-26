package com.example.movieapp.network

import com.example.movieapp.model.ResponseItem
import com.example.movieapp.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.END_POINT)
    suspend fun getMovies(): Response<ResponseItem>

    @GET(Constants.END_POINT2)
    suspend fun getByPage(): Response<ResponseItem>

    @GET(Constants.END_SEARCH)
    suspend fun searchMovies(@Query("q") query: String): Response<ResponseItem>
}