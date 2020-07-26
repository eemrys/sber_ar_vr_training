package com.example.exercise10.coroutines

interface TaskEventContract {
    fun onPreExecute()
    fun onPostExecute()
    fun onProgressUpdate(progress: Int)
}