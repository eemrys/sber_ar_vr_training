package com.example.exercise11

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity

class DownloadReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val filePath = intent?.getStringExtra(PATH) ?: return
        //MainActivity.startActivity(context, intent?.extras)
    }
}