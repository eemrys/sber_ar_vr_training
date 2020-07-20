package com.example.exercise7.coroutines

interface TaskEventContract {
    fun onPreExecute()
    fun onPostExecute()
    fun onProgressUpdate(progress: Int)
}