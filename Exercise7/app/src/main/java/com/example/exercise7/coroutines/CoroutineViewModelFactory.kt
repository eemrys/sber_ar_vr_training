package com.example.exercise7.coroutines

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CoroutineViewModelFactory (private val context: Context) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CoroutineViewModel::class.java)) {
            return CoroutineViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
