package com.example.exercise10.work

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class BackgroundProgressReceiver : BroadcastReceiver() {

    private val _progress = MutableLiveData<Int>()
    val progress: LiveData<Int>
        get() = _progress

    override fun onReceive(context: Context, intent: Intent) {
        _progress.value = intent.getIntExtra(PROGRESS_VALUE_KEY, -1)
    }
}