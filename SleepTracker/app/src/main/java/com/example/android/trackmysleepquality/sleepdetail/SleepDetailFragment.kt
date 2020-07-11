package com.example.android.trackmysleepquality.sleepdetail

import android.content.res.Resources
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.convertDurationToFormatted
import com.example.android.trackmysleepquality.convertNumericQualityToString
import com.example.android.trackmysleepquality.database.SleepDatabase
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.setIconImage
import kotlinx.android.synthetic.main.fragment_sleep_detail.*
import kotlinx.coroutines.Dispatchers

class SleepDetailFragment : Fragment(R.layout.fragment_sleep_detail) {

    private val dataSource by lazy (Dispatchers.IO) {
        val application = requireNotNull(this.activity).application
        SleepDatabase.getInstance(application).sleepDatabaseDao
    }
    private val viewModelFactory by lazy {
        val arguments = SleepDetailFragmentArgs.fromBundle(requireArguments())
        SleepDetailViewModelFactory(arguments.sleepNightKey, dataSource)
    }
    private val sleepDetailViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
                .get(SleepDetailViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClick()
        setObserver()
    }

    private fun setOnClick() {
        btnClose.setOnClickListener {
            sleepDetailViewModel.onClose()
        }
    }

    private fun setObserver() {
        sleepDetailViewModel.apply {
            currentNight.observe(viewLifecycleOwner, Observer {
                it?.apply {
                    updateViews(it)
                }
            })
            navigateToSleepTracker.observe(viewLifecycleOwner,  Observer {
                if (it) {
                    navigateToSleepTrackerFragment()
                    sleepDetailViewModel.doneNavigating()
                }
            })
        }
    }

    private fun updateViews(night: SleepNight) {
        val res: Resources = this.requireContext().resources
        txtvLength.text = convertDurationToFormatted(night.startTimeMilli, night.endTimeMilli, res)
        txtvQuality.text = convertNumericQualityToString(night.sleepQuality, res)
        imgvQuality.setIconImage(night.sleepQuality)
    }

    private fun navigateToSleepTrackerFragment() {
        findNavController().navigate(R.id.fragmentSleepTracker)
    }
}