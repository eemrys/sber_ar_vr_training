package com.example.exercise5.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.exercise5.R
import com.example.exercise5.data.Movie
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val viewModelFactory by lazy {
        val arguments: Movie = requireArguments().get("selectedMovie") as Movie
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

    private fun setViewsData(movie: Movie) {
        movie.apply {
            imgvWidePoster.setImageResource(posterWide)
            imgvPoster.setImageResource(poster)
            txtvTitle.text = title
            txtvDate.text = releaseDate
            txtvSummary.text = summary

            btnTrailer.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(trailerUrl))
                startActivity(intent)
            }
        }
    }
}