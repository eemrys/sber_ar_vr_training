package com.example.exercise6

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exercise6.data.DataStorage
import com.example.exercise6.data.Movie

class MainViewModel : ViewModel() {
    private val _listMovies = MutableLiveData<List<Movie>>()
    val listMovies: LiveData<List<Movie>>
        get() = _listMovies

    init {
        _listMovies.value = DataStorage.getMoviesList()
    }
}