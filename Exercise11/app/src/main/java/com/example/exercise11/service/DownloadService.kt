package com.example.exercise11.service

import android.app.*
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.exercise11.*
import com.example.exercise11.IMAGE_PROGRESS
import com.example.exercise11.IMAGE_URL
import com.example.exercise11.ui.MainFragment
import com.example.exercise11.PATH
import java.io.File

private const val ONGOING_NOTIFICATION_ID = 987
private const val CHANNEL_DEFAULT_IMPORTANCE = "01_Channel"
private const val ERROR_NOTIFICATION_ID = 1024

class DownloadService : Service() {

    private val notificationManager by lazy {
        NotificationManagerCompat.from(applicationContext)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val url = intent?.getStringExtra(IMAGE_URL) ?: return START_NOT_STICKY
        val path = intent.getStringExtra(PATH) ?: return START_NOT_STICKY
        startForeground()
        startDownload(url, File(path))
        return START_STICKY
    }

    private fun startForeground() {
        createNotificationChannel()
        val notification = createNotification(0)
        startForeground(ONGOING_NOTIFICATION_ID, notification)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_DEFAULT_IMPORTANCE,
                getString(R.string.channel_name),
                NotificationManager.IMPORTANCE_HIGH)
            channel.apply {
                description = getString(R.string.channel_description)
                lightColor = Color.RED
                vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
                enableLights(true)
                enableVibration(true)
            }
            notificationManager.createNotificationChannel(channel)
        }
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
    
    private fun startDownload(posterUrl: String, path: File) {
        DownloadThread(posterUrl, callback, path).start()
    }

    private val callback = object : DownloadCallBack {
        override fun onProgressUpdate(percent: Int) {
            updateNotification(percent)
        }
        override fun onDownloadFinished(filePath: String?) {
            filePath?.apply {
                sendBroadcastMsgDownloadComplete(this)
            }
            stopSelf()
        }
        override fun onError(error: String?) {
            notificationManager.notify(ERROR_NOTIFICATION_ID, createErrorNotification())
            notificationManager.cancel(ONGOING_NOTIFICATION_ID)
            stopSelf()
        }
    }

    private fun updateNotification(progress: Int) {
        val notification = createNotification(progress)
        notificationManager.notify(ONGOING_NOTIFICATION_ID, notification)
    }

    private fun sendBroadcastMsgDownloadComplete(posterPath: String) {
        val broadcastIntent = Intent(IMAGE_PROGRESS)
        broadcastIntent.putExtra(PATH, posterPath)
        sendBroadcast(broadcastIntent)
    }

    private fun createErrorNotification(): Notification {
        return NotificationCompat.Builder(this, CHANNEL_DEFAULT_IMPORTANCE)
            .setContentTitle(getText(R.string.notification_error_title))
            .setContentText(getText(R.string.notification_error_message))
            .setSmallIcon(R.drawable.ic_stat_download)
            .build()
    }
}