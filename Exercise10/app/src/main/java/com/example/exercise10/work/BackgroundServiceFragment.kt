package com.example.exercise10.work

import android.content.*
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.exercise10.R
import com.example.exercise10.work.services.BackgroundJobIntentService
import com.example.exercise10.work.services.BackgroundProgressReceiver
import com.example.exercise10.work.services.BackgroundService
import kotlinx.android.synthetic.main.fragment_background_service.*

class BackgroundServiceFragment : Fragment(R.layout.fragment_background_service) {

    private val backgroundProgressReceiver by lazy {
        BackgroundProgressReceiver()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setObserver()
    }

    override fun onResume() {
        subscribeForProgressUpdates()
        super.onResume()
    }

    override fun onPause() {
        requireContext().unregisterReceiver(backgroundProgressReceiver)
        super.onPause()
    }

    private fun setListeners() {
        btnService.setOnClickListener {
            it.isEnabled = false
            val intent = Intent(context, BackgroundService::class.java)
            requireActivity().startService(intent)
        }
        btnIntent.setOnClickListener {
            it.isEnabled = false
            val intent = Intent()
            BackgroundJobIntentService.enqueueWork(requireContext(), intent)
        }
    }

    private fun setObserver() {
        backgroundProgressReceiver.apply {
            progressService.observe(viewLifecycleOwner, Observer {
                txtvProgressService.text = getString(R.string.percent, it)
                if (it == MAX_VALUE) {
                    val intent = Intent(context, BackgroundService::class.java)
                    requireActivity().stopService(intent)
                    btnService.isEnabled = true
                }
            })
            progressIntent.observe(viewLifecycleOwner, Observer {
                txtvProgressIntent.text = getString(R.string.percent, it)
                if (it == MAX_VALUE) {
                    btnIntent.isEnabled = true
                }
            })
        }
    }

    private fun subscribeForProgressUpdates() {
        val filter = IntentFilter()
        filter.addAction(PROGRESS_UPDATE_SERVICE)
        filter.addAction(PROGRESS_UPDATE_INTENT)
        requireContext().registerReceiver(backgroundProgressReceiver, filter)
    }

    private fun createWorkRequest() {
        val workRequest: WorkRequest =
            OneTimeWorkRequestBuilder<BackgroundWorker>()
                .build()
        WorkManager
            .getInstance(requireContext())
            .enqueue(workRequest)
    }
}