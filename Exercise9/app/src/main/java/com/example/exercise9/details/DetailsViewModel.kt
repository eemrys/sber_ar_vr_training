package com.example.exercise9.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exercise9.domain.Movie
import com.example.exercise9.domain.Trailer
import com.example.exercise9.network.MovieApiStatus
import com.example.exercise9.network.MovieApi
import com.example.exercise9.network.MovieMapper
import kotlinx.coroutines.launch

class DetailsViewModel(movie: Movie) : ViewModel() {

    private val mapper by lazy {
        MovieMapper()
    }
    private val _selectedMovie = MutableLiveData<Movie>()
    val selectedMovie: LiveData<Movie>
        get() = _selectedMovie

    private val _trailerStatus = MutableLiveData<MovieApiStatus>()
    val trailerStatus: LiveData<MovieApiStatus>
        get() = _trailerStatus

    private val _movieTrailer = MutableLiveData<Trailer?>()
    val movieTrailer: LiveData<Trailer?>
        get() = _movieTrailer

    init {
        _selectedMovie.value = movie
        getMovieTrailer(movie)
    }

    private fun getMovieTrailer(movieItem: Movie) {
        viewModelScope.launch {
            val getMovieTrailerDeferred = MovieApi.retrofitService.getMovieTrailerAsync(movieItem.id)
            try {
                _trailerStatus.value = MovieApiStatus.LOADING
                val listResult = getMovieTrailerDeferred.await()
                listResult.apply {
                    _movieTrailer.value = mapper.mapTrailerUrl(listResult.results.first())
                }
                _trailerStatus.value = MovieApiStatus.DONE
            } catch (e: Exception) {
                _trailerStatus.value = MovieApiStatus.ERROR
                _movieTrailer.value = null
            }
        }
    }
}