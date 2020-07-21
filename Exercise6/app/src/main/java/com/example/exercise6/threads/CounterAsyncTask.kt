package com.example.exercise6.threads

import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import com.example.exercise6.TaskEventContract

class CounterAsyncTask(private val listener: TaskEventContract) {

    @Volatile
    var isCancelled = false
        private set
    var savedPoint: Int? = null
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

    init {
        runOnUiThread(Runnable { listener.onPreExecute() })
    }

    fun execute() {
        runOnUiThread(runnable)
    }

    private fun doInBackground() {
        val start = savedPoint ?: 0
        for (i in start..10) {
            if (isCancelled) { return }
            publishProgress(i)
            SystemClock.sleep(500)
        }
        runOnUiThread(Runnable { listener.onPostExecute() })
    }

    private fun publishProgress(progress: Int) {
        runOnUiThread(Runnable { listener.onProgressUpdate(progress) })
    }

    private fun runOnUiThread(runnable: Runnable) {
        handler.post(runnable)
    }

    fun cancel(destroy: Boolean = false) {
        handler.removeCallbacks(runnable)
        isCancelled = true
        backgroundThread?.interrupt()
        if (!destroy) {
            listener.onCancelled()
        }
    }
}