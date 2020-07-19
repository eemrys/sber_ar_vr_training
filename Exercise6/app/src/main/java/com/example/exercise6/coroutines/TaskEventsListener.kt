package com.example.exercise6.coroutines

interface TaskEventContract {
    fun onPreExecute()
    fun onPostExecute()
    fun onProgressUpdate(progress: Int)
}