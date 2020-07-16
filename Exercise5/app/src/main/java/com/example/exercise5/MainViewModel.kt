package com.example.exercise5

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exercise5.data.DataStorage
import com.example.exercise5.data.Movie

class MainViewModel : ViewModel() {
    private val _listMovies = MutableLiveData<List<Movie>>()
    val listMovies: LiveData<List<Movie>>
        get() = _listMovies

    init {
        _listMovies.value = DataStorage.getMoviesList()
    }
}