package com.example.exercise8.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exercise8.network.MovieApiStatus
import com.example.exercise8.data.MovieItem
import com.example.exercise8.data.TrailerItem
import com.example.exercise8.network.MovieApi
import com.example.exercise8.network.MovieMapper
import kotlinx.coroutines.launch

class DetailsViewModel(movie: MovieItem) : ViewModel() {

    private val mapper by lazy {
        MovieMapper()
    }
    private val _selectedMovie = MutableLiveData<MovieItem>()
    val selectedMovie: LiveData<MovieItem>
        get() = _selectedMovie

    private val _trailerStatus = MutableLiveData<MovieApiStatus>()
    val trailerStatus: LiveData<MovieApiStatus>
        get() = _trailerStatus

    private val _movieTrailer = MutableLiveData<TrailerItem?>()
    val movieTrailer: LiveData<TrailerItem?>
        get() = _movieTrailer

    init {
        _selectedMovie.value = movie
    }

    private fun getMovieTrailer(movieItem: MovieItem) {
        viewModelScope.launch {
            val getMovieTrailerDeferred = MovieApi.retrofitService.getMovieTrailerAsync(movieItem.id)
            try {
                _trailerStatus.value = MovieApiStatus.LOADING
                val listResult = getMovieTrailerDeferred.await()
                listResult.apply {
                    _movieTrailer.value = TrailerItem(mapper.mapTrailerUrl(listResult.results.first()))
                }
                _trailerStatus.value = MovieApiStatus.DONE
            } catch (e: Exception) {
                _trailerStatus.value = MovieApiStatus.ERROR
                _movieTrailer.value = null
            }
        }
    }
}