package com.example.exercise6.coroutines

import com.example.exercise6.TaskEventContract
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CoroutineTask(private val listener: TaskEventContract) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob()

    var savedPoint: Int? = null

    private val newJob = launch(Dispatchers.IO, CoroutineStart.LAZY) {
        val start = savedPoint ?: 0
        for (i in start..10) {
            launch(Dispatchers.Main) {
                listener.onProgressUpdate(i)
            }
            delay(500)
        }
        launch(Dispatchers.Main) {
            listener.onPostExecute()
        }
    }

    fun createTask() {
        listener.onPreExecute()
    }

    fun startTask() {
        newJob.start()
    }

    fun cancelTask(destroy: Boolean = false) {
        if (!destroy) {
            listener.onCancelled()
        }
        newJob.cancel()
        coroutineContext.cancel()
    }
}

