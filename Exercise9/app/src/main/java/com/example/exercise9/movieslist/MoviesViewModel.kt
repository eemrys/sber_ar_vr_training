package com.example.exercise9.movieslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MoviesViewModel : ViewModel() {

    private val _navigateToDetail = MutableLiveData<Int?>()
    val navigateToDetail: LiveData<Int?>
        get() = _navigateToDetail

    fun onMovieClicked(position: Int) {
        _navigateToDetail.value = position
    }

    fun onDetailsNavigated() {
        _navigateToDetail.value = null
    }
}