package com.example.exercise11

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat.finishAfterTransition
import androidx.fragment.app.Fragment
import coil.api.load
import kotlinx.android.synthetic.main.fragment_image.*
import java.io.File

class ImageFragment : Fragment(R.layout.fragment_image) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val posterPath = requireActivity().intent?.getStringExtra(PATH)
        if (posterPath == null) {
            AlertDialog.Builder(requireContext())
                .setMessage(R.string.generic_error)
                .setPositiveButton(R.string.dialog_ok) { _, _ ->
                    finishAfterTransition(requireActivity())
                }
                .create()
                .show()
            return
        }
        imgvMain.load(File(posterPath))
    }
}