package com.example.exercise8.gallery

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.exercise8.details.DetailsFragment
import com.example.exercise8.data.MovieItem

class GalleryPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    var movies = listOf<MovieItem>()
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