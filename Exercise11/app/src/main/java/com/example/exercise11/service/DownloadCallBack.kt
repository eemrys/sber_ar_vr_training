package com.example.exercise11.service

interface DownloadCallBack {
    fun onProgressUpdate(percent: Int)
    fun onDownloadFinished(filePath: String?)
    fun onError(error: String?)
}