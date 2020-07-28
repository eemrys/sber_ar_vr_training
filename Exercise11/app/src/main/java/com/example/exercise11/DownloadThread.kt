package com.example.exercise11

open class DownloadThread(
    private val url: String,
    private val downloadCallBack: DownloadCallBack
) : Thread() {
    override fun run() {}

    interface DownloadCallBack {
        fun onProgressUpdate(percent: Int)
        fun onDownloadFinished(filePath: String?)
        fun onError(error: String?)
    }
}
