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

    private val adapterMovies by lazy {
        MoviesAdapter { viewModel.onMovieClicked(it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setObserver()

        recyclervMovies.apply {
            adapter = adapterMovies
            layoutManager = LinearLayoutManager(this@MoviesActivity)
        }
    }

    private fun setObserver() {
        viewModel.apply {
            navigateToDetail.observe(this@MoviesActivity, Observer {
                it?.apply {
                    navigateToDetailScreen(it)
                    viewModel.onDetailsNavigated()
                }
            })
            listMovies.observe(this@MoviesActivity, Observer {
                adapterMovies.data = it
            })
        }
    }

    private fun navigateToDetailScreen(movie: Movie) {
        val intent = Intent(this, DetailsActivity::class.java)
            .putExtra("selected movie", movie)
        startActivity(intent)
    }
}