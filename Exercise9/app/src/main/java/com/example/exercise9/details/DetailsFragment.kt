package com.example.exercise9.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.exercise9.R
import com.example.exercise9.data.MovieItem
import kotlinx.android.synthetic.main.fragment_details.*
import coil.api.load
import com.example.exercise9.network.MovieApiStatus

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val viewModelFactory by lazy {
        val arguments: MovieItem = requireArguments().get("selectedMovie") as MovieItem
        DetailsViewModelFactory(arguments)
    }
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(DetailsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addObserver()
    }

    private fun addObserver() {
        viewModel.apply {
            selectedMovie.observe(viewLifecycleOwner, Observer { setViewsData(it) })
            trailerStatus.observe(viewLifecycleOwner, Observer { setStatus(it) })
            movieTrailer.observe(viewLifecycleOwner, Observer {
                it?.apply {
                    setOnClick(url)
                }
            })
        }
    }

    private fun setViewsData(movie: MovieItem) {
        movie.apply {
            txtvTitle.text = title
            txtvDate.text = releaseDate
            txtvSummary.text = summary
            imgvPoster.load(poster) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)
            }
            imgvWidePoster.load(posterWide) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)
            }
        }
    }

    private fun setStatus(status: MovieApiStatus) {
        btnTrailer.text = when(status) {
            MovieApiStatus.LOADING -> getString(R.string.loading)
            MovieApiStatus.ERROR -> getString(R.string.error)
            else -> getString(R.string.watch_trailer)
        }
    }

    private fun setOnClick(trailerUrl: String) {
        btnTrailer.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(trailerUrl))
            startActivity(intent)
        }
    }
}