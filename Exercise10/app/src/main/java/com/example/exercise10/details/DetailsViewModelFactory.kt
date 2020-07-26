package com.example.exercise10.details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.exercise10.domain.Movie

class DetailsViewModelFactory(private val application: Application, private val movie: Movie) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(application, movie) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}