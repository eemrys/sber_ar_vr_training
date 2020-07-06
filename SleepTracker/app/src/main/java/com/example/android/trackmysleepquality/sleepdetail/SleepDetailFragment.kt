package com.example.android.trackmysleepquality.sleepdetail

import android.content.res.Resources
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.convertDurationToFormatted
import com.example.android.trackmysleepquality.convertNumericQualityToString
import com.example.android.trackmysleepquality.database.SleepDatabase
import com.example.android.trackmysleepquality.database.SleepNight
import kotlinx.android.synthetic.main.fragment_sleep_detail.*

class SleepDetailFragment : Fragment(R.layout.fragment_sleep_detail) {

    private val application by lazy {
        requireNotNull(this.activity).application
    }
    private val dataSource by lazy {
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
        updateViews()
    }

    private fun setOnClick() {
        btnClose.setOnClickListener {
            sleepDetailViewModel.onClose()
        }
    }

    private fun setObserver() {
        sleepDetailViewModel.navigateToSleepTracker.observe(viewLifecycleOwner,  Observer {
            if (it == true) {
                navigateToSleepTrackerFragment()
                sleepDetailViewModel.doneNavigating()
            }
        })
    }

    private fun updateViews() {

        val res: Resources = this.context.resources
        val night = sleepDetailViewModel.getNight()

        // TODO safe call

        txtvLength.text = convertDurationToFormatted(night.value.startTimeMilli, night.value.endTimeMilli, res)
        txtvQuality.text = convertNumericQualityToString(night.value.sleepQuality, res)
        imgvQuality.setImageResource(when (night.value.sleepQuality) {
            0 -> R.drawable.ic_sleep_0
            1 -> R.drawable.ic_sleep_1
            2 -> R.drawable.ic_sleep_2
            3 -> R.drawable.ic_sleep_3
            4 -> R.drawable.ic_sleep_4
            5 -> R.drawable.ic_sleep_5
            else -> R.drawable.ic_sleep_active
        })
    }

    private fun navigateToSleepTrackerFragment() {
        val action = SleepDetailFragmentDirections.actionSleepDetailFragmentToSleepTrackerFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }
}