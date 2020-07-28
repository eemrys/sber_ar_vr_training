package com.example.exercise10.work.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.exercise10.work.PROGRESS_KEY_INTENT
import com.example.exercise10.work.PROGRESS_KEY_SERVICE
import com.example.exercise10.work.PROGRESS_UPDATE_SERVICE

class BackgroundProgressReceiver : BroadcastReceiver() {

    private val _progressService = MutableLiveData<Int>()
    val progressService: LiveData<Int>
        get() = _progressService

    private val _progressIntent = MutableLiveData<Int>()
    val progressIntent: LiveData<Int>
        get() = _progressIntent

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            PROGRESS_UPDATE_SERVICE -> _progressService.value = intent.getIntExtra(
                PROGRESS_KEY_SERVICE, -1)
            else ->  _progressIntent.value = intent.getIntExtra(PROGRESS_KEY_INTENT, -1)
        }
    }
}