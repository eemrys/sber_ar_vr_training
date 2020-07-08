/*
 *  Copyright 2018, The Android Open Source Project
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.example.android.marsrealestate.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.marsrealestate.R
import com.example.android.marsrealestate.bindImage
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val viewModelFactory by lazy {
        val application = requireNotNull(activity).application
        val arguments = DetailFragmentArgs.fromBundle(requireArguments())
        DetailViewModelFactory(arguments.selectedProperty, application)
    }
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
                .get(DetailViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObserver()
    }

    private fun addObserver() {
        viewModel.apply{
            selectedProperty.observe(viewLifecycleOwner, Observer {
                bindImage(imgvMainPhoto, it.imgSrcUrl)
            })
            displayPropertyPrice.observe(viewLifecycleOwner, Observer {
                txtvPriceValue.text = it
            })
            displayPropertyType.observe(viewLifecycleOwner, Observer {
                txtvPropertyType.text = it
            })
        }
    }
}