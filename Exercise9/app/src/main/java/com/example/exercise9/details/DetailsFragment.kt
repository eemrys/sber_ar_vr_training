package com.example.exercise9.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.exercise9.R
import kotlinx.android.synthetic.main.fragment_details.*
import coil.api.load
import com.example.exercise9.domain.Movie

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val viewModel by lazy {
        val arguments: Movie = requireArguments().get("selectedMovie") as Movie
        val application = requireNotNull(this.activity).application
        ViewModelProvider(this, DetailsViewModelFactory(application, arguments))
            .get(DetailsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addObserver()
    }

    private fun addObserver() {
        viewModel.apply {
            selectedMovie.observe(viewLifecycleOwner, Observer { setViewsData(it) })
            movieTrailer.observe(viewLifecycleOwner, Observer {
                it?.apply {
                    setOnClick(url)
                }
            })
        }
    }

    private fun setViewsData(movie: Movie) {
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

    private fun setOnClick(trailerUrl: String) {
        btnTrailer.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(trailerUrl))
            startActivity(intent)
        }
    }
}