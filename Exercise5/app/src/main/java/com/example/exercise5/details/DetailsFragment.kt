package com.example.exercise5.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.exercise5.R
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val viewModelFactory by lazy {
        val arguments = DetailsFragmentArgs.fromBundle(requireArguments())
        DetailsViewModelFactory(arguments.selectedMovie)
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
        viewModel.selectedMovie.observe(viewLifecycleOwner, Observer {
            imgvWidePoster.setImageResource(it.posterWide)
            imgvPoster.setImageResource(it.poster)
            txtvTitle.text = it.title
            txtvDate.text = it.releaseDate
            txtvSummary.text = it.summary
            val url = it.trailerUrl
            btnTrailer.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }
        })
    }
}