package com.example.exercise6.threads

import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import com.example.exercise6.TaskEventContract

class CounterAsyncTask(private val listener: TaskEventContract, var currentPoint: Int) {

    @Volatile
    var isCancelled = false
        private set
    private var backgroundThread: Thread? = null

    init {
        runOnUiThread(Runnable { listener.onPreExecute() })
    }

    fun execute() {
        runOnUiThread(Runnable {
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
        val start = currentPoint
        for (i in start..10) {
            if (isCancelled) { return }
            publishProgress(i)
            SystemClock.sleep(500)
            currentPoint = i
        }
        currentPoint = 0
    }

    private fun publishProgress(progress: Int) {
        runOnUiThread(Runnable { listener.onProgressUpdate(progress) })
    }

    fun cancel() {
        isCancelled = true
        backgroundThread?.interrupt()
    }
}