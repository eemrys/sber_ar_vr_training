package com.example.exercise5.movieslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exercise5.data.DataStorage
import com.example.exercise5.data.Movie

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