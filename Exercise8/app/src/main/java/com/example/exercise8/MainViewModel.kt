package com.example.exercise8

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exercise8.network.MovieApi
import com.example.exercise8.network.MovieItem
import kotlinx.coroutines.launch

enum class MovieApiStatus { LOADING, ERROR, DONE }

class MainViewModel : ViewModel() {

    private val _listMovies = MutableLiveData<List<MovieItem>>()
    val listMovies: LiveData<List<MovieItem>>
        get() = _listMovies

    private val _status = MutableLiveData<MovieApiStatus>()
    val status: LiveData<MovieApiStatus>
        get() = _status

    init {
        getMovieList()
    }

    private fun getMovieList() {
        viewModelScope.launch {
            val getPropertiesDeferred = MovieApi.retrofitService.getMovieListAsync()
            try {
                _status.value = MovieApiStatus.LOADING
                val listResult = getPropertiesDeferred.await()
                _status.value = MovieApiStatus.DONE
                listResult.results.apply {
                    _listMovies.value = this
                }
            } catch (e: Exception) {
                _status.value = MovieApiStatus.ERROR
                _listMovies.value = ArrayList()
            }
        }
    }
}