package com.example.exercise7.gallery

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.exercise7.data.Movie
import com.example.exercise7.details.DetailsFragment

class GalleryPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    var movies = emptyList<Movie>()

    override fun getItemCount(): Int = movies.size

    override fun createFragment(position: Int): Fragment {
        val fragment = DetailsFragment()
        fragment.arguments = bundleOf("selectedMovie" to movies[position])
        return fragment
    }
}