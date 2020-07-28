package com.example.exercise10.work.services

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import androidx.core.app.JobIntentService
import com.example.exercise10.work.MAX_VALUE
import com.example.exercise10.work.PROGRESS_KEY_INTENT
import com.example.exercise10.work.PROGRESS_UPDATE_ACTION

class BackgroundJobIntentService : JobIntentService() {

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

    override fun onHandleWork(intent: Intent) {
        handler.post(runnable)
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
            SystemClock.sleep(100)
        }
    }

    private fun showProgressNumber(progress: Int) {
        val broadcastIntent = Intent(PROGRESS_UPDATE_ACTION)
        broadcastIntent.putExtra(PROGRESS_KEY_INTENT, progress)
        sendBroadcast(broadcastIntent)
    }
}