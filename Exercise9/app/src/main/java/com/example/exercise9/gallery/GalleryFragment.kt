package com.example.exercise9.gallery

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.exercise9.MainViewModel
import com.example.exercise9.MainViewModelFactory
import com.example.exercise9.R
import kotlinx.android.synthetic.main.fragment_gallery.*

class GalleryFragment : Fragment(R.layout.fragment_gallery) {

    private val sharedViewModel: MainViewModel by lazy {
        val application = requireNotNull(this.activity).application
        ViewModelProvider(this, MainViewModelFactory(application))
            .get(MainViewModel::class.java)
    }

    private val pagerAdapter by lazy {
        GalleryPagerAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
        vpagerGallery.adapter = pagerAdapter
    }

    private fun setObserver() {
        sharedViewModel.listMovies.observe(viewLifecycleOwner, Observer {
            pagerAdapter.movies = it
            val arguments = GalleryFragmentArgs.fromBundle(requireArguments()).position
            vpagerGallery.setCurrentItem(arguments, false)
        })
    }
}