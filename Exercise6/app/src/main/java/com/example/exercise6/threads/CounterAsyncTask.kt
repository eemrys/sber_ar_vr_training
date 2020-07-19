package com.example.exercise6.threads

import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import com.example.exercise6.TaskEventContract

class CounterAsyncTask(private val listener: TaskEventContract) {

    @Volatile
    var isCancelled = false
        private set
    private var backgroundThread: Thread? = null

    fun execute() {
        runOnUiThread(Runnable {
            listener.onPreExecute()
            backgroundThread = object : Thread("Handler_executor_thread") {
                override fun run() {
                    doInBackground()
                    runOnUiThread(Runnable { listener.onPostExecute() })
                }
            }
            backgroundThread!!.start()
        })
    }

    private fun runOnUiThread(runnable: Runnable) {
        Handler(Looper.getMainLooper()).post(runnable)
    }

    private fun doInBackground() {
        for (i in 0..10) {
            if (isCancelled) { return }
            publishProgress(i)
            SystemClock.sleep(500)
        }
    }

    private fun publishProgress(progress: Int) {
        runOnUiThread(Runnable { listener.onProgressUpdate(progress) })
    }

    fun cancel() {
        isCancelled = true
        backgroundThread?.interrupt()
    }
}