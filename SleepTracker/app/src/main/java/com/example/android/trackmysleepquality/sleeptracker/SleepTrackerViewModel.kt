/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.sleeptracker

import android.app.Application
import androidx.lifecycle.*
import com.example.android.trackmysleepquality.database.SleepDatabaseDao
import com.example.android.trackmysleepquality.database.SleepNight
import kotlinx.coroutines.*

class SleepTrackerViewModel(
        val database: SleepDatabaseDao,
        application: Application) : AndroidViewModel(application) {

    private var currentNight = MutableLiveData<SleepNight?>()

    val allNights = database.getAllNights()

    private val _navigateToSleepQuality = MutableLiveData<SleepNight>()
    val navigateToSleepQuality: LiveData<SleepNight>
        get() = _navigateToSleepQuality

    private var _showSnackbarEvent = MutableLiveData<Boolean>()
    val showSnackBarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent

    private val _navigateToSleepDetail = MutableLiveData<Long>()
    val navigateToSleepDetail: LiveData<Long>
        get() = _navigateToSleepDetail

    val startButtonVisible = Transformations.map(currentNight) {
        null == it
    }
    val stopButtonVisible = Transformations.map(currentNight) {
        null != it
    }
    val clearButtonVisible = Transformations.map(allNights) {
        it.isNotEmpty()
    }

    init {
        initializeTonight()
    }

    private fun initializeTonight() {
        viewModelScope.launch {
            val night = database.getTonight()
            currentNight.value = if (night?.endTimeMilli != night?.startTimeMilli) null else night
        }
    }

    fun onStartTracking() {
        viewModelScope.launch {
            val newNight = SleepNight()
            database.insert(newNight)
            val night = database.getTonight()
            currentNight.value = if (night?.endTimeMilli != night?.startTimeMilli) null else night
        }
    }

    fun onStopTracking() {
        viewModelScope.launch {
            val oldNight = currentNight.value ?: return@launch
            oldNight.endTimeMilli = System.currentTimeMillis()
            database.update(oldNight)
            _navigateToSleepQuality.value = oldNight
        }
    }

    fun onClear() {
        viewModelScope.launch {
            database.clear()
            currentNight.value = null
            _showSnackbarEvent.value = true
        }
    }

    fun doneNavigating() {
        _navigateToSleepQuality.value = null
    }

    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = false
    }

    fun onSleepNightClicked(id: Long){
        _navigateToSleepDetail.value = id
    }

    fun onSleepNightNavigated() {
        _navigateToSleepDetail.value = null
    }
}