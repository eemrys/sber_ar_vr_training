package com.example.exercise4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_movies.*

class MoviesActivity : AppCompatActivity(R.layout.activity_movies) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val moviesAdapter = MoviesAdapter()
        val manager = LinearLayoutManager(this)
        moviesAdapter.data = createMovieList()
        recyclervMovies.apply {
            adapter = moviesAdapter
            layoutManager = manager
        }
    }
}