package com.example.exercise6

interface TaskEventContract {
    fun onPreExecute()
    fun onPostExecute()
    fun onProgressUpdate(progress: Int)
}