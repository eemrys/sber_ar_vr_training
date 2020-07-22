package com.example.exercise8.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exercise8.network.MovieItem

class DetailsViewModel(movie: MovieItem) : ViewModel() {

    private val _selectedMovie = MutableLiveData<MovieItem>()
    val selectedMovie: LiveData<MovieItem>
        get() = _selectedMovie

    init {
        _selectedMovie.value = movie
    }
}