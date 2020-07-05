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

package com.example.android.trackmysleepquality.sleeptracker

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.database.SleepDatabase
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_sleep_tracker.*

class SleepTrackerFragment : Fragment(R.layout.fragment_sleep_tracker) {

    private val application by lazy {
        requireNotNull(this.activity).application
    }
    private val dataSource by lazy {
        SleepDatabase.getInstance(application).sleepDatabaseDao
    }
    private val viewModelFactory by lazy {
        SleepTrackerViewModelFactory(dataSource, application)
    }
    private val sleepTrackerViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
                .get(SleepTrackerViewModel::class.java)
    }
    private val adapter by lazy {
        SleepNightAdapter()
    }
    private val manager by lazy {
        GridLayoutManager(this.activity, 3)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClick()
        setObserver()
        recyclervSleepList.adapter = adapter
        recyclervSleepList.layoutManager = manager
    }

    private fun setOnClick() {
        btnStart.setOnClickListener {
            sleepTrackerViewModel.onStartTracking()
        }
        btnStop.setOnClickListener {
            sleepTrackerViewModel.onStopTracking()
        }
        btnClear.setOnClickListener {
            sleepTrackerViewModel.onClear()
        }
    }

    private fun setObserver() {
        sleepTrackerViewModel.apply {
            startButtonVisible.observe(viewLifecycleOwner, Observer { setVisibilityStart(it) })
            stopButtonVisible.observe(viewLifecycleOwner, Observer { setVisibilityStop(it) })
            clearButtonVisible.observe(viewLifecycleOwner, Observer { setVisibilityClear(it) })
            allNights.observe(viewLifecycleOwner, Observer {
                it?.let {
                    adapter.submitList(it)
                }
            })
            navigateToSleepQuality.observe(viewLifecycleOwner, Observer {
                it?.let {
                    navigateToSleepQualityFragment(it.nightId)
                    sleepTrackerViewModel.doneNavigating()
                }
            })
            showSnackBarEvent.observe(viewLifecycleOwner, Observer {
                if (it == true) {
                    makeSnackbar()
                    sleepTrackerViewModel.doneShowingSnackbar()
                }
            })
        }
    }

    private fun setVisibilityStart(enabled: Boolean) {
        btnStart.isEnabled = enabled
    }

    private fun setVisibilityStop(enabled: Boolean) {
        btnStop.isEnabled = enabled
    }

    private fun setVisibilityClear(enabled: Boolean) {
        btnClear.isEnabled = enabled
    }

    private fun navigateToSleepQualityFragment(id: Long) {
        val action = SleepTrackerFragmentDirections.actionSleepTrackerFragmentToSleepQualityFragment(id)
        findNavController(this).navigate(action)
    }

    private fun makeSnackbar() {
        Snackbar.make(
                requireActivity().findViewById(android.R.id.content),
                getString(R.string.cleared_message),
                Snackbar.LENGTH_SHORT
        ).show()
    }
}