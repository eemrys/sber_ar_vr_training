package com.example.exercise8

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exercise8.network.MovieApi
import com.example.exercise8.data.MovieItem
import com.example.exercise8.network.MovieMapper
import kotlinx.coroutines.launch

enum class MovieApiStatus { LOADING, ERROR, DONE }

class MainViewModel : ViewModel() {

    private val mapper by lazy {
        MovieMapper()
    }
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
                listResult.apply {
                    _listMovies.value = mapper.mapToMovies(this)
                }
                _status.value = MovieApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MovieApiStatus.ERROR
                _listMovies.value = ArrayList()
            }
        }
    }
}