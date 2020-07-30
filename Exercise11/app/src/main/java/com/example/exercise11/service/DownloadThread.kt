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
        requireNotNull(file) {
            downloadCallBack.onError("Can't create file")
            return
        }
        val url = URL(url)

        val connection = getConnection(url) ?: return

        downloadStream(file, url, connection)

        downloadCallBack.onDownloadFinished(file.path)
    }

    private fun createFile(): File? {
        val dir = path
        require(dir.exists()) {
            require(dir.mkdirs()) {
                return null
            }
        }
        val imageName = createImageFileName() + ".jpg"
        return File(dir.path + File.separator + imageName)
    }

    private fun createImageFileName(): String {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        return "FILE_$timeStamp"
    }

    private fun getConnection(url: URL): HttpsURLConnection? {
        val connection = url.openConnection() as HttpsURLConnection
        connection.connect()
        if (connection.responseCode != HttpURLConnection.HTTP_OK) {
            downloadCallBack.onError("Server returned HTTP response code: "
                    + connection.responseCode + " - " + connection.responseMessage
            )
            return null
        }
        return connection
    }

    private fun downloadStream(file: File, url: URL, connection: HttpsURLConnection) {
        val inputStream = BufferedInputStream(url.openStream(), 8192)
        val fos = FileOutputStream(file.path)
        val data = ByteArray(1024)
        var next: Int
        while (inputStream.read(data).also { next = it } != -1) {
            fos.write(data, 0, next)
            updateProgress(fos, connection.contentLength)
        }
        fos.apply {
            flush()
            close()
        }
        inputStream.close()
    }

    @Throws(IOException::class)
    private fun updateProgress(fos: FileOutputStream, fileLength: Int) {
        val needsUpdate = (lastUpdateTime == 0L) || (System.currentTimeMillis() > lastUpdateTime + 500)
        if (needsUpdate) {
            val count = fos.channel.size().toInt() * 100 / fileLength
            if (count > progress) {
                progress = count
                lastUpdateTime = System.currentTimeMillis()
                downloadCallBack.onProgressUpdate(progress)
            }
        }
    }
}
