package com.example.exercise10.work.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import android.os.Looper

class BackgroundService : Service() {

    private val binder = ServiceBinder()
    private var counter = 0
    private val maxProgress: Int = 100
    private val handler: Handler = Handler(Looper.getMainLooper())
    private val runnable = Runnable {
        showProgressNumber(
            if (counter < maxProgress) {
                counter++
            } else {
                counter
            }
        )
    }

    inner class ServiceBinder : Binder() {
        val service: BackgroundService
            get() = this@BackgroundService
    }

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        counter = 0
        handler.postDelayed(runnable, 1000)
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
    }

    private fun showProgressNumber(progress: Int) {
        //AndroidServiceDelegate.setProgressToService(progress)//to do inject
        handler.postDelayed(runnable, 1000)
    }
}