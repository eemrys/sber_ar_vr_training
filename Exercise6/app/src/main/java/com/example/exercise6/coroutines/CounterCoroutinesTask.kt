package com.example.exercise6.coroutines

import com.example.exercise6.TaskEventContract
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CoroutineTask(private val listener: TaskEventContract, var currentPoint: Int) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob()

    private var newJob: Job? = null

    fun createTask() {
        newJob = launch(Dispatchers.IO, CoroutineStart.LAZY) {
            val start = currentPoint
            for (i in start..10) {
                launch(Dispatchers.Main) {
                    listener.onProgressUpdate(i)
                }
                delay(500)
                currentPoint = i
            }
            currentPoint = 0
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

