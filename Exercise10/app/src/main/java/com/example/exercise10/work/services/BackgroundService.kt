package com.example.exercise10.work.services

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import com.example.exercise10.work.DELAY_VALUE
import com.example.exercise10.work.MAX_VALUE
import com.example.exercise10.work.PROGRESS_UPDATE_SERVICE
import com.example.exercise10.work.PROGRESS_KEY_SERVICE

class BackgroundService : Service() {

    private var isCancelled = false
    private var backgroundThread: Thread? = null
    private val handler =  Handler(Looper.getMainLooper())
    private val runnable = Runnable {
        backgroundThread = object : Thread("Handler_executor_thread") {
            override fun run() {
                doInBackground()
            }
        }
        backgroundThread!!.start()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        handler.post(runnable)
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
        isCancelled = true
        backgroundThread?.interrupt()
    }

    private fun doInBackground() {
        for (i in 0..MAX_VALUE) {
            if (isCancelled) { return }
            showProgressNumber(i)
            Thread.sleep(DELAY_VALUE)
        }
    }

    private fun showProgressNumber(progress: Int) {
        val broadcastIntent = Intent(PROGRESS_UPDATE_SERVICE)
        broadcastIntent.putExtra(PROGRESS_KEY_SERVICE, progress)
        sendBroadcast(broadcastIntent)
    }
}