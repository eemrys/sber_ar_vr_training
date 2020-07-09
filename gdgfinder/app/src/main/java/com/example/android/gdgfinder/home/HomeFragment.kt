package com.example.android.gdgfinder.home

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.gdgfinder.R

class HomeFragment : Fragment(R.layout.fragment_home) {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }
}