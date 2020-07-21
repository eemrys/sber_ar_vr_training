package com.example.exercise7.coroutines

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CoroutineTask(private val listener: TaskEventContract) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob()

    private var newJob: Job? = null

    fun createTask() {
        newJob = launch(Dispatchers.IO, CoroutineStart.LAZY) {
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
        listener.onPreExecute()
    }

    fun startTask() {
        newJob?.start()
    }

    fun cancelTask() {
        newJob?.cancel()
        coroutineContext.cancel()
    }
}

