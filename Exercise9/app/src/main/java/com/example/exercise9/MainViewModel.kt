package com.example.exercise9

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exercise9.domain.Movie
import com.example.exercise9.network.MovieApi
import com.example.exercise9.network.dto.MovieDto
import com.example.exercise9.network.MovieApiStatus
import com.example.exercise9.network.MovieMapper
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val mapper by lazy {
        MovieMapper()
    }
    private val _listMovies = MutableLiveData<List<Movie>>()
    val listMovies: LiveData<List<Movie>>
        get() = _listMovies

    private val _status = MutableLiveData<MovieApiStatus>()
    val status: LiveData<MovieApiStatus>
        get() = _status

    init {
        getMovieList()
    }

    private fun getMovieList() {
        viewModelScope.launch {
            val getMovieListDeferred = MovieApi.retrofitService.getMovieListAsync()
            try {
                _status.value = MovieApiStatus.LOADING
                val listResult = getMovieListDeferred.await()
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