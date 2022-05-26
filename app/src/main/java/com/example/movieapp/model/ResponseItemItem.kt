package com.example.movieapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movie_table")
data class ResponseItemItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val averageRuntime: Int,
    val dvdCountry: Any,
    val ended: String,
    val genres: List<String> = arrayListOf(),
    val image: Image,
    val language: String,
    val name: String,
    val officialSite: String,
    val premiered: String,
    val runtime: Int,
    val status: String,
    val summary: String,
    val type: String,
    val updated: Int,
    val url: String,
    val webChannel: Any,
    val weight: Int
)