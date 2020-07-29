package com.example.exercise11.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.exercise11.PATH

class DownloadReceiver : BroadcastReceiver() {

    private val _filePath = MutableLiveData<String>()
    val filePath: LiveData<String>
        get() = _filePath


    override fun onReceive(context: Context, intent: Intent) {
        _filePath.value = intent.getStringExtra(PATH) ?: return
    }
}