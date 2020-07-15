package com.example.exercise4.details

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exercise4.data.Movie
import com.example.exercise4.R
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity(R.layout.activity_details) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movie = intent.getParcelableExtra<Movie>("selected movie")

        movie?.apply {
            txtvTitle.text = title
            txtvSummary.text = summary
            imgvPoster.setImageResource(poster)

            btnTrailer.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(trailerUrl))
                startActivity(intent)
            }
        }
    }
}