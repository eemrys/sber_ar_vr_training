package com.example.exercise8.coroutines

interface TaskEventContract {
    fun onPreExecute()
    fun onPostExecute()
    fun onProgressUpdate(progress: Int)
}