package com.example.exercise9

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exercise9.db.AppDatabase.Companion.getInstance
import com.example.exercise9.network.MovieApi
import com.example.exercise9.network.MovieMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(application: Application) : ViewModel() {

    private val database = getInstance(application)

    private val mapper by lazy {
        MovieMapper()
    }
    val listMovies = database.movieDao.getAll()

    init {
        viewModelScope.launch {
            refreshFeed()
        }
    }

    private suspend fun refreshFeed() {
        withContext(Dispatchers.IO) {
            val listResult = MovieApi.retrofitService.getMovieListAsync().await()
            database.movieDao.insertAll(*mapper.mapToMovies(listResult))
        }
    }
}