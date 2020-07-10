package com.example.android.gdgfinder.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.gdgfinder.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClick()
        setObserver()
    }

    private fun setOnClick() {
        btnFab.setOnClickListener {
            viewModel.onFabClicked()
        }
    }

    private fun setObserver() {
        viewModel.navigateToSearch.observe(viewLifecycleOwner, Observer {
            if (it) {
                navigateToSearchFragment()
                viewModel.onNavigatedToSearch()
            }
        })
    }

    private fun navigateToSearchFragment() {
        findNavController().navigate(R.id.fragmentSearch)
    }
}