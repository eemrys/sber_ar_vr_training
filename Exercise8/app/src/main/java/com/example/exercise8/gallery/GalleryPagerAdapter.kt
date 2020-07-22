package com.example.exercise8.gallery

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.exercise8.network.Movie
import com.example.exercise8.details.DetailsFragment

class GalleryPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    var movies = emptyList<Movie>()

    override fun getItemCount(): Int = movies.size

    override fun createFragment(position: Int): Fragment {
        val fragment = DetailsFragment()
        fragment.arguments = bundleOf("selectedMovie" to movies[position])
        return fragment
    }
}