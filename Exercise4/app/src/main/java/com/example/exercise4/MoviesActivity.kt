package com.example.exercise4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_movies.*

class MoviesActivity : AppCompatActivity(R.layout.activity_movies) {

    private val clickListenerIntent: (movie: Movie) -> Unit = {
        val intent = Intent(this, DetailsActivity::class.java)
            .putExtra("selected movie", it)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val moviesAdapter = MoviesAdapter(createMovieList(), clickListenerIntent)
        recyclervMovies.apply {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(this@MoviesActivity)
        }
    }
}