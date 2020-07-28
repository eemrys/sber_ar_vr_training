package com.example.exercise11

import java.io.File
import java.text.SimpleDateFormat
import java.util.*

open class DownloadThread(
    private val url: String,
    private val downloadCallBack: DownloadCallBack,
    private val path: File
) : Thread() {
    override fun run() {}

    private fun createFile(): File? {
        val dir = path
        if (!dir.exists()) {
            if (!dir.mkdirs()) { return null }
        }
        val imageName = createImageFileName() + ".jpg"
        return File(dir.path + File.separator + imageName)
    }

    private fun createImageFileName(): String {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        return "FILE_$timeStamp"
    }

    interface DownloadCallBack {
        fun onProgressUpdate(percent: Int)
        fun onDownloadFinished(filePath: String?)
        fun onError(error: String?)
    }
}
