package com.example.exercise10.work.services

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.core.app.JobIntentService

class BackgroundJobIntentService : JobIntentService() {

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

    override fun onHandleWork(intent: Intent) {
        TODO("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        counter = 0
        handler.postDelayed(runnable, 1000)
        return Service.START_STICKY
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