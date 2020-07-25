package com.example.exercise9.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.exercise9.domain.Movie
import com.example.exercise9.network.dto.MovieDto

class DetailsViewModelFactory(private val movie: Movie) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(movie) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}