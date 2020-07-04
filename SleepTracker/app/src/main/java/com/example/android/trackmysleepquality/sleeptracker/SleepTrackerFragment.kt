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
import android.text.Spanned
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.database.SleepDatabase
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClick()
        setObserver()
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
        sleepTrackerViewModel.nightsString.observe(viewLifecycleOwner, Observer {
            updateText(it)
        })
    }

    private fun updateText(nights: Spanned) {
        txtvNights.text = nights
    }
}