package com.example.exercise10.work.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class BackgroundService : Service() {

    private val binder = ServiceBinder()

    inner class ServiceBinder : Binder() {
        val service: BackgroundService
            get() = this@BackgroundService
    }

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }
}