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
 */

package com.example.android.trackmysleepquality.sleepquality

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.database.SleepDatabase
import kotlinx.android.synthetic.main.fragment_sleep_quality.*

class SleepQualityFragment : Fragment(R.layout.fragment_sleep_quality) {

    private val application by lazy {
        requireNotNull(this.activity).application
    }
    private val dataSource by lazy {
        SleepDatabase.getInstance(application).sleepDatabaseDao
    }
    private val viewModelFactory by lazy {
        val arguments = SleepQualityFragmentArgs.fromBundle(requireArguments())
        SleepQualityViewModelFactory(arguments.sleepNightKey, dataSource)
    }
    private val sleepQualityViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
                .get(SleepQualityViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClick()
        setObserver()
    }

    private fun setOnClick() {
        setQuality(imgvQualityZero, 0)
        setQuality(imgvQualityOne, 1)
        setQuality(imgvQualityTwo, 2)
        setQuality(imgvQualityThree, 3)
        setQuality(imgvQualityFour, 4)
        setQuality(imgvQualityFive, 5)
    }

    private fun setQuality(image: ImageView, quality: Int) {
        image.setOnClickListener {
            sleepQualityViewModel.onSetSleepQuality(quality)
        }
    }

    private fun setObserver() {
        sleepQualityViewModel.navigateToSleepTracker.observe(viewLifecycleOwner, Observer {
            if (it) {
                navigateToSleepTrackerFragment()
                sleepQualityViewModel.doneNavigating()
            }
        })
    }

    private fun navigateToSleepTrackerFragment() {
        findNavController().navigate(R.id.fragmentSleepTracker)
    }
}