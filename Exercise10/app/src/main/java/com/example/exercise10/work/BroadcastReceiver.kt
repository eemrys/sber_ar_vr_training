package com.example.exercise10.work

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class BackgroundProgressReceiver : BroadcastReceiver() {

    private val _progressService = MutableLiveData<Int>()
    val progressService: LiveData<Int>
        get() = _progressService

    private val _progressIntent = MutableLiveData<Int>()
    val progressIntent: LiveData<Int>
        get() = _progressIntent

    override fun onReceive(context: Context, intent: Intent) {
        _progressService.value = intent.getIntExtra(PROGRESS_KEY_SERVICE, -1)
        _progressIntent.value = intent.getIntExtra(PROGRESS_KEY_INTENT, -1)
    }
}