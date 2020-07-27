package com.example.exercise10.work.services

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import com.example.exercise10.work.PROGRESS_UPDATE_ACTION
import com.example.exercise10.work.PROGRESS_VALUE_KEY

class BackgroundService : Service() {

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

    override fun onBind(intent: Intent?): IBinder? {
        return null
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
        val broadcastIntent = Intent(PROGRESS_UPDATE_ACTION)
        broadcastIntent.putExtra(PROGRESS_VALUE_KEY, progress)
        sendBroadcast(broadcastIntent)
        handler.postDelayed(runnable, 1000)
    }
}