package com.example.exercise10.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.exercise10.work.DELAY_VALUE
import com.example.exercise10.work.MAX_VALUE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class BackgroundWorker(appContext: Context, workerParams: WorkerParameters):
    CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        for (i in 0..MAX_VALUE) {
            val progressAsync = async {
                delay(DELAY_VALUE)
                i
            }
            val progressOutput = progressAsync.await()
            // publish progress
        }
        Result.success()
    }
}