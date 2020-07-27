package com.example.exercise10.work.services

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.core.app.JobIntentService
import com.example.exercise10.work.PROGRESS_UPDATE_ACTION
import com.example.exercise10.work.PROGRESS_VALUE_KEY

const val JOB_ID = 10

class BackgroundJobIntentService : JobIntentService() {

    companion object {
        fun enqueueWork(context: Context, intent: Intent) {
            enqueueWork(context, BackgroundJobIntentService::class.java, JOB_ID, intent)
        }
    }

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
        counter = 0
        handler.postDelayed(runnable, 1000)
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