package com.example.exercise10.work.services

import android.content.Intent
import androidx.core.app.JobIntentService

class BackgroundJobIntentService : JobIntentService() {
    override fun onHandleWork(intent: Intent) {
        TODO("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }
}