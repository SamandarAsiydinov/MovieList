package com.example.movieapp.repository

import com.example.movieapp.database.MovieDao
import com.example.movieapp.model.ResponseItemItem
import com.example.movieapp.network.ApiService
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val apiService: ApiService,
    private val movieDao: MovieDao
) {

    suspend fun getMovies() = apiService.getMovies()
    suspend fun getByPage() = apiService.getByPage()

    suspend fun getAllMovies() = movieDao.getAllMovies()
    suspend fun saveMovie(responseItem: ResponseItemItem) = movieDao.saveMovie(responseItem)
    suspend fun deleteMovie(responseItem: ResponseItemItem) = movieDao.deleteMovie(responseItem)
    suspend fun updateMovie(responseItem: ResponseItemItem) = movieDao.updateMovie(responseItem)
    suspend fun deleteAllMovies() = movieDao.deleteAllMovies()
}