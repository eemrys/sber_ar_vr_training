package com.example.exercise9.list

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exercise9.MainViewModel
import com.example.exercise9.network.MovieApiStatus
import com.example.exercise9.R
import com.example.exercise9.gallery.GalleryFragmentArgs
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : Fragment(R.layout.fragment_movies) {

    private val sharedViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private val viewModel by lazy {
        ViewModelProvider(this).get(MoviesViewModel::class.java)
    }

    private val adapterMovies by lazy {
        MoviesAdapter { viewModel.onMovieClicked(it) }
    }

    private val navOptions by lazy {
        NavOptions.Builder().setEnterAnim(R.anim.nav_default_enter_anim)
            .setPopEnterAnim(R.anim.nav_default_pop_enter_anim).build()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObserver()

        recyclervMovies.apply {
            adapter = adapterMovies
            layoutManager = LinearLayoutManager(view.context)
        }

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController().navigate(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setObserver() {
        viewModel.navigateToDetail.observe(viewLifecycleOwner, Observer {
                it?.apply {
                    navigateToDetailGallery(it)
                    viewModel.onDetailsNavigated()
                }
            })
        sharedViewModel.apply {
            listMovies.observe(viewLifecycleOwner, Observer {
                adapterMovies.data = it
            })
            status.observe(viewLifecycleOwner, Observer {
                setImageStatus(it)
            })
        }
    }

    private fun setImageStatus(status: MovieApiStatus) {
        when(status) {
            MovieApiStatus.LOADING -> setVisibilityAndImage(View.VISIBLE, R.drawable.loading_animation)
            MovieApiStatus.ERROR -> setVisibilityAndImage(View.VISIBLE, R.drawable.ic_connection_error)
            MovieApiStatus.DONE -> setVisibilityAndImage(View.GONE, null)
        }
    }

    private fun setVisibilityAndImage(visibility: Int, drawable: Int?) {
        imgvStatus.visibility = visibility
        drawable?.apply {
            imgvStatus.setImageResource(this)
        }
    }

    private fun navigateToDetailGallery(position: Int) {
        val args = GalleryFragmentArgs.Builder(position).build().toBundle()
        findNavController().navigate(R.id.fragmentGallery, args, navOptions)
    }
}