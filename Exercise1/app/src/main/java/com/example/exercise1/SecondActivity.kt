package com.example.exercise1

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity(R.layout.activity_second) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        txtvLink.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org"))
            startActivity(intent)
        }
    }
}