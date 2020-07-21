package com.example.exercise8.coroutines

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CoroutineTask(private val listener: TaskEventContract) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob()

    private val newJob = launch(Dispatchers.IO, CoroutineStart.LAZY) {
        for (i in 0..20) {
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

    fun startTask(): Boolean {
        return newJob.start()
    }

    fun cancelTask() {
        newJob.cancel()
        coroutineContext.cancel()
    }
}

