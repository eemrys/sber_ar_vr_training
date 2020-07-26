package com.example.exercise9.details

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exercise9.db.AppDatabase
import com.example.exercise9.domain.Movie
import com.example.exercise9.domain.Trailer
import com.example.exercise9.network.MovieApiStatus
import com.example.exercise9.network.MovieApi
import com.example.exercise9.network.MovieMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsViewModel(application: Application, movie: Movie) : ViewModel() {

    private val database = AppDatabase.getInstance(application)

    private val mapper by lazy {
        MovieMapper()
    }

    private val _selectedMovie = MutableLiveData<Movie>()
    val selectedMovie: LiveData<Movie>
        get() = _selectedMovie

    private val _trailerStatus = MutableLiveData<MovieApiStatus>()
    val trailerStatus: LiveData<MovieApiStatus>
        get() = _trailerStatus

    val movieTrailer = database.trailerDao.getTrailerById(movie.id)

    init {
        _selectedMovie.value = movie
        getMovieTrailer(movie)
    }

    private fun getMovieTrailer(movieItem: Movie) {
        viewModelScope.launch {
            try {
                _trailerStatus.value = MovieApiStatus.LOADING
                refreshTrailer(movieItem.id)
                _trailerStatus.value = MovieApiStatus.DONE
            } catch (e: Exception) {
                _trailerStatus.value = MovieApiStatus.ERROR
            }
        }
    }

    private suspend fun refreshTrailer(id: String) {
        withContext(Dispatchers.IO) {
            val listResult = MovieApi.retrofitService.getMovieTrailerAsync(id).await()
            val url = mapper.mapTrailerUrl(listResult.results.first())
            database.trailerDao.insert(Trailer(id, url))
        }
    }
}