package com.example.exercise11.service

import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import javax.net.ssl.HttpsURLConnection

open class DownloadThread(private val url: String,
                          private val downloadCallBack: DownloadCallBack,
                          private val path: File) : Thread() {

    private var progress = 0
    private var lastUpdateTime: Long = 0

    override fun run() {
        val file = createFile()
        if (file == null) {
            downloadCallBack.onError("Can't create file")
            return
        }
        val url = URL(url)
        val connection = url.openConnection() as HttpsURLConnection?
        connection?.connect()

        if (connection?.responseCode != HttpURLConnection.HTTP_OK) {
            downloadCallBack.onError("Server returned HTTP response code: "
                    + connection?.responseCode + " - " + connection?.responseMessage)
            return
        }
        val fileLength = connection.contentLength
        val inputStream = BufferedInputStream(url.openStream(), 8192)
        val fos = FileOutputStream(file.path)
        var next: Int
        val data = ByteArray(1024)
        while (inputStream.read(data).also { next = it } != -1) {
            fos.write(data, 0, next)
            updateProgress(fos, fileLength)
        }

        downloadCallBack.onDownloadFinished(file.path)

    }

    private fun createFile(): File? {
        val dir = path
        if (!dir.exists()) {
            if (!dir.mkdirs()) { return null }
        }
        val imageName = createImageFileName() + ".jpg"
        return File(dir.path + File.separator + imageName)
    }

    @Throws(IOException::class)
    private fun updateProgress(fos: FileOutputStream, fileLength: Int) {
        if (lastUpdateTime == 0L || System.currentTimeMillis() > lastUpdateTime + 500) {
            val count = fos.channel.size().toInt() * 100 / fileLength
            if (count > progress) {
                progress = count
                lastUpdateTime = System.currentTimeMillis()
                downloadCallBack.onProgressUpdate(progress)
            }
        }
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
