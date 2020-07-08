/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.marsrealestate.overview

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android.marsrealestate.R
import com.example.android.marsrealestate.network.MarsProperty
import kotlinx.android.synthetic.main.fragment_overview.*

class OverviewFragment : Fragment(R.layout.fragment_overview) {

    private val viewModel: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }

    private val adapter by lazy {
        PhotoGridAdapter(GridClickListener { viewModel.onItemClicked(it) })
    }

    private val navOptions by lazy {
        NavOptions.Builder().setEnterAnim(R.anim.nav_default_enter_anim)
                .setPopEnterAnim(R.anim.nav_default_pop_enter_anim).build()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        val manager = GridLayoutManager(this.activity, 2)
        recyclervPhotos.layoutManager = manager
        recyclervPhotos.adapter = adapter
        addObserver()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun addObserver() {
        viewModel.apply{
            properties.observe(viewLifecycleOwner, Observer {
                adapter.submitList(it)
            })
            status.observe(viewLifecycleOwner, Observer {
                setImageStatus(it)
            })
            navigateToDetail.observe(viewLifecycleOwner, Observer {
                it?.apply {
                    navigateToDetailFragment(it)
                    viewModel.onItemNavigated()
                }
            })
        }
    }

    private fun setImageStatus(status: MarsApiStatus) {
        imgvStatus.apply {
            when(status) {
                MarsApiStatus.LOADING -> {
                    visibility = View.VISIBLE
                    setImageResource(R.drawable.loading_animation)
                }
                MarsApiStatus.ERROR -> {
                    visibility = View.VISIBLE
                    setImageResource(R.drawable.ic_connection_error)
                }
                MarsApiStatus.DONE -> {
                    visibility = View.GONE
                }
            }
        }
    }
    private fun navigateToDetailFragment(property: MarsProperty) {
        val bundle = bundleOf("selectedProperty" to property)
        findNavController().navigate(R.id.fragmentDetail, bundle, navOptions)
    }
}
