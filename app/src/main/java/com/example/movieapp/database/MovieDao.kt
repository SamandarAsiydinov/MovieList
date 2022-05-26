package com.example.movieapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.movieapp.model.ResponseItemItem

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveMovie(responseItem: ResponseItemItem)

    @Query("SELECT * FROM Movie_table")
    suspend fun getAllMovies(): LiveData<List<ResponseItemItem>>

    @Update
    suspend fun updateMovie(responseItem: ResponseItemItem)

    @Delete
    suspend fun deleteMovie(responseItem: ResponseItemItem)

    @Query("DELETE FROM Movie_table")
    suspend fun deleteAllMovies()
}