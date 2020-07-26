package com.example.exercise10.details

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exercise10.db.AppDatabase
import com.example.exercise10.domain.Movie
import com.example.exercise10.domain.Trailer
import com.example.exercise10.network.MovieApi
import com.example.exercise10.network.MovieMapper
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

    val movieTrailer = database.trailerDao.getTrailerById(movie.id)

    init {
        _selectedMovie.value = movie
        viewModelScope.launch {
            refreshTrailer(movie.id)
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