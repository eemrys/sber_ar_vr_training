package com.example.exercise11.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.exercise11.ui.ImageFragment
import com.example.exercise11.ui.PATH

class DownloadReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val filePath = intent?.getStringExtra(PATH) ?: return
        val imageFragmentIntent = Intent(context, ImageFragment::class.java)
        imageFragmentIntent.putExtra(PATH, filePath)
        context?.startActivity(imageFragmentIntent)
    }
}