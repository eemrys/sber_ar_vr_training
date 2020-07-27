package com.example.exercise10.work

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import androidx.fragment.app.Fragment
import com.example.exercise10.R
import com.example.exercise10.work.services.BackgroundService
import kotlinx.android.synthetic.main.fragment_background_service.*

class BackgroundServiceFragment : Fragment(R.layout.fragment_background_service),
    ServiceConnection {

    var service: BackgroundService? = null
    var isConnected = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
    }

    override fun onStop() {
        super.onStop()
        // TODO unbind serivces
    }

    private fun setListeners() {
        btnService.setOnClickListener {
            it.isEnabled = false
            val intent = Intent(context, BackgroundService::class.java)
            bindService(intent, this, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        isConnected = false
        service = null
    }

    override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
        isConnected = true
        service = (binder as BackgroundService.ServiceBinder).service
    }

    private fun bindService (intent: Intent, conn: ServiceConnection, lags: Int): Boolean {
        return true
    }
}