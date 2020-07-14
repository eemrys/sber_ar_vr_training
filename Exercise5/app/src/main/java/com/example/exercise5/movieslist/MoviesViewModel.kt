package com.example.exercise5.movieslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exercise5.data.DataStorage
import com.example.exercise5.data.Movie

class MoviesViewModel : ViewModel() {

    private val _listMovies = MutableLiveData<List<Movie>>()
    val listMovies: LiveData<List<Movie>>
        get() = _listMovies

    private val _navigateToDetail = MutableLiveData<Movie?>()
    val navigateToDetail: LiveData<Movie?>
        get() = _navigateToDetail

    init {
        _listMovies.value = DataStorage.getMoviesList()
    }

    fun onMovieClicked(movie: Movie) {
        _navigateToDetail.value = movie
    }

    fun onDetailsNavigated() {
        _navigateToDetail.value = null
    }
}