package com.example.exercise4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.list_item_view.*

class DetailsActivity : AppCompatActivity(R.layout.activity_details) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movie = intent.getParcelableExtra<Movie>("selected movie")

        movie?.apply {
            txtvTitle.text = movie.title
            txtvSummary.text = movie.summary
            imgvPoster.setMoviePoster(movie.posterId)
        }
    }
}