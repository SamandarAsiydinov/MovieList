package com.example.movieapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.ResponseItem
import com.example.movieapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _response: MutableLiveData<ResponseItem> = MutableLiveData()
    val response: LiveData<ResponseItem> get() = _response
    private val _responsePage: MutableLiveData<ResponseItem> = MutableLiveData()
    val responsePage: LiveData<ResponseItem> get() = _responsePage

    init {
        getMovies()
        getByPage()
    }
    private fun getMovies() = viewModelScope.launch {
        repository.getMovies().let { response ->
            if (response.isSuccessful) {
                _response.postValue(response.body())
            } else {
                Log.d("@@@@@", "Error")
            }
        }
    }
    private fun getByPage() = viewModelScope.launch {
        repository.getByPage().let { response ->
            if (response.isSuccessful) {
                _responsePage.postValue(response.body())
            } else {
                Log.d("@@@@@", "Error")
            }
        }
    }
    fun getAllMovies() = viewModelScope.launch {
        repository.getAllMovies().let { }
    }
}