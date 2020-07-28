package com.example.exercise11

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_image.*

class ImageFragment : Fragment(R.layout.fragment_image) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
    }

    private fun setListener() {
        btnFab.setOnClickListener {
            // start work
        }
    }
}