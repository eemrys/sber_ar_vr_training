package com.example.exercise10.work

import android.content.*
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.exercise10.R
import com.example.exercise10.work.services.BackgroundJobIntentService
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
        context?.unregisterReceiver(backgroundProgressReceiver)
        super.onPause()
    }

    private fun setListeners() {
        btnService.setOnClickListener {
            it.isEnabled = false
            val intent = Intent(context, BackgroundService::class.java)
            activity?.startService(intent)
        }
        btnIntent.setOnClickListener {
            it.isEnabled = false
            val intent = Intent(context, BackgroundJobIntentService::class.java)
            activity?.startService(intent)
        }
    }

    private fun setObserver() {
        backgroundProgressReceiver.progress.observe(viewLifecycleOwner, Observer {
            txtvProgress.text = it.toString()
        })
    }

    private fun subscribeForProgressUpdates() {
        val progressUpdateActionFilter = IntentFilter(PROGRESS_UPDATE_ACTION)
        context?.registerReceiver(backgroundProgressReceiver, progressUpdateActionFilter)
    }
}