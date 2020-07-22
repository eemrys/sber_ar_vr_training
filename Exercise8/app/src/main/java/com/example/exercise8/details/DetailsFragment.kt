package com.example.exercise8.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.exercise8.R
import com.example.exercise8.network.MovieItem
import kotlinx.android.synthetic.main.fragment_details.*
import coil.api.load

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
        viewModel.selectedMovie.observe(viewLifecycleOwner, Observer { setViewsData(it) })
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
            /*btnTrailer.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(trailerUrl))
                startActivity(intent)
            }*/
        }
    }
}