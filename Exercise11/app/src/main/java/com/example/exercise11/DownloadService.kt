package com.example.exercise11

import android.app.*
import android.app.DownloadManager.ACTION_DOWNLOAD_COMPLETE
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.io.File

private const val ONGOING_NOTIFICATION_ID = 987
private const val CHANNEL_DEFAULT_IMPORTANCE = "01_Channel"

class DownloadService : Service() {

    private val notificationManager by lazy {
        NotificationManagerCompat.from(applicationContext)
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val url = intent?.getStringExtra(IMAGE_URL)
        val path = intent?.getStringExtra(PATH)
        startForeground()
        if (url != null && path != null) {
            val filePath = File(path)
            startDownload(url, filePath)
        } else {
            return START_NOT_STICKY
        }
        return START_STICKY
    }

    private fun startForeground() {
        createNotificationChannel()
        val notification = createNotification(0)
        startForeground(ONGOING_NOTIFICATION_ID, notification)
    }

    private fun startDownload(posterUrl: String, path: File) {
        DownloadThread(posterUrl, object : DownloadThread.DownloadCallBack {
            override fun onProgressUpdate(percent: Int) {

            }

            override fun onDownloadFinished(filePath: String?) {

            }

            override fun onError(error: String?) {

            }
        }, path).start()

    }

    private fun createNotification(progress: Int): Notification {
        val notificationIntent = Intent(this, MainFragment::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)
        return NotificationCompat.Builder(this, CHANNEL_DEFAULT_IMPORTANCE)
            .setContentTitle(getString(R.string.notification_title, progress))
            .setContentText(getText(R.string.notification_message))
            .setSmallIcon(R.drawable.ic_stat_download)
            .setContentIntent(pendingIntent)
            .build()
    }

    private fun updateNotification(progress: Int) {
        val notification = createNotification(progress)
        notificationManager.notify(ONGOING_NOTIFICATION_ID, notification)
    }

    private fun createNotificationChannel() : NotificationChannel? {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = getString(R.string.channel_name)
            val description = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_HIGH
            return NotificationChannel(
                CHANNEL_DEFAULT_IMPORTANCE,
                name,
                importance
            ).apply {
                this.description = description
                this.lightColor = Color.RED
                this.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
                enableLights(true)
                enableVibration(true)
            }
        }
        return null
    }

    private fun sendBroadcastMsgDownloadComplete(posterPath: String) {
        val intent: Intent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Intent(this, DownloadReceiver::class.java)
        } else {
            Intent(ACTION_DOWNLOAD_COMPLETE)
        }
        intent.putExtra(PATH, posterPath)
        sendBroadcast(intent)
    }
}