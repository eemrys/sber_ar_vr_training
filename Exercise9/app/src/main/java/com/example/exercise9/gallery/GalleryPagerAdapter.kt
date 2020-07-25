package com.example.exercise9.gallery

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.exercise9.details.DetailsFragment
import com.example.exercise9.domain.Movie

class GalleryPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    var movies = listOf<Movie>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = movies.size

    override fun createFragment(position: Int): Fragment {
        val fragment = DetailsFragment()
        fragment.arguments = bundleOf("selectedMovie" to movies[position])
        return fragment
    }
}