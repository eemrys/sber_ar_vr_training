package com.example.exercise6.coroutines

import com.example.exercise6.TaskEventContract
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CoroutineTask(private val listener: TaskEventContract) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob()

    private var newJob: Job? = null

    fun createTask() {
        newJob = launch(Dispatchers.IO, CoroutineStart.LAZY) {
            repeat(10) { counter ->
                launch(Dispatchers.Main) {
                    listener.onProgressUpdate(counter)
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

