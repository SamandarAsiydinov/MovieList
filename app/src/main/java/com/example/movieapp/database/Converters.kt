package com.example.movieapp.database

import androidx.room.TypeConverter
import com.example.movieapp.model.ResponseItemItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromGroupList(value: List<ResponseItemItem>): String {
        val gson = Gson()
        val type = object : TypeToken<List<ResponseItemItem>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toGroupList(value: String): List<ResponseItemItem> {
        val gson = Gson()
        val type = object : TypeToken<List<ResponseItemItem>>() {}.type
        return gson.fromJson(value, type)
    }
}