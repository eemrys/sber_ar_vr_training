package com.example.exercise4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_movies.*

class MoviesActivity : AppCompatActivity(R.layout.activity_movies) {

    private val viewModel by lazy {
        ViewModelProvider(this).get(MoviesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setObserver()

        recyclervMovies.apply {
            adapter = MoviesAdapter( viewModel.data) { viewModel.onMovieClicked(it) }
            layoutManager = LinearLayoutManager(this@MoviesActivity)
        }
    }

    private fun setObserver() {
        viewModel.navigateToDetail.observe(this, Observer {
            it?.apply {
                navigateToDetailScreen(it)
                viewModel.onDetailsNavigated()
            }
        })
    }

    private fun navigateToDetailScreen(movie: Movie) {
        val intent = Intent(this, DetailsActivity::class.java)
            .putExtra("selected movie", movie)
        startActivity(intent)
    }
}