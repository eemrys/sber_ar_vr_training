package com.example.exercise9

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exercise9.db.AppDatabase.Companion.getInstance
import com.example.exercise9.network.MovieApi
import com.example.exercise9.network.MovieApiStatus
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

    private val _status = MutableLiveData<MovieApiStatus>()
    val status: LiveData<MovieApiStatus>
        get() = _status

    init {
        getMovieList()
    }

    private fun getMovieList() {
        viewModelScope.launch {
            try {
                _status.value = MovieApiStatus.LOADING
                refreshFeed()
                _status.value = MovieApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MovieApiStatus.ERROR
            }
        }
    }

    private suspend fun refreshFeed() {
        withContext(Dispatchers.IO) {
            val listResult = MovieApi.retrofitService.getMovieListAsync().await()
            database.movieDao.insertAll(mapper.mapToMovies(listResult))
        }
    }
}