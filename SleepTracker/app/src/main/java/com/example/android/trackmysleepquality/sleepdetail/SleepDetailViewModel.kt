package com.example.android.trackmysleepquality.sleepdetail

import androidx.lifecycle.*
import com.example.android.trackmysleepquality.database.SleepDatabaseDao
import com.example.android.trackmysleepquality.database.SleepNight
import kotlinx.coroutines.launch

class SleepDetailViewModel(
        private val sleepNightKey: Long = 0L,
        val database: SleepDatabaseDao) : ViewModel() {

    private val _currentNight = MutableLiveData<SleepNight>()
    val currentNight: LiveData<SleepNight>
        get() = _currentNight

    private val _navigateToSleepTracker = MutableLiveData<Boolean>()
    val navigateToSleepTracker: LiveData<Boolean>
        get() = _navigateToSleepTracker


    init {
        viewModelScope.launch {
            _currentNight.value = database.getById(sleepNightKey)
        }
    }

    fun doneNavigating() {
        _navigateToSleepTracker.value = false
    }

    fun onClose() {
        _navigateToSleepTracker.value = true
    }

}
