package com.example.exercise11

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class DownloadReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val filePath = intent?.getStringExtra(PATH) ?: return
        val imageFragmentIntent = Intent(context, ImageFragment::class.java)
        imageFragmentIntent.putExtra(PATH, filePath)
        context?.startActivity(imageFragmentIntent)
    }
}