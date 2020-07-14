package com.example.exercise4.details

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exercise4.data.Movie
import com.example.exercise4.R
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.list_item_view.imgvPoster
import kotlinx.android.synthetic.main.list_item_view.txtvSummary
import kotlinx.android.synthetic.main.list_item_view.txtvTitle

class DetailsActivity : AppCompatActivity(R.layout.activity_details) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movie = intent.getParcelableExtra<Movie>("selected movie")

        movie?.apply {
            txtvTitle.text = movie.title
            txtvSummary.text = movie.summary
            imgvPoster.setImageResource(movie.poster)
        }

        btnTrailer.setOnClickListener {
            val url = movie?.trailerUrl ?: return@setOnClickListener
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }
}