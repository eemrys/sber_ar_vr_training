package com.example.exercise11.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.app.ActivityCompat.finishAfterTransition
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil.api.load
import com.example.exercise11.R
import kotlinx.android.synthetic.main.fragment_image.*
import java.io.File

class ImageFragment : Fragment(R.layout.fragment_image) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val posterPath = ImageFragmentArgs.fromBundle(requireArguments()).path

        showPoster(posterPath)
        setOnBackPressed()
    }

    private fun showPoster(path: String) {
        try {
            imgvMain.load(File(path))
        } catch (e: Exception) {
            AlertDialog.Builder(requireContext())
                .setMessage(R.string.generic_error)
                .setPositiveButton(R.string.dialog_ok) { _, _ ->
                    finishAfterTransition(requireActivity())
                }
                .create()
                .show()
        }
    }

    private fun setOnBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navigateBack()
            }
        })
    }

    private fun navigateBack() {
        findNavController().navigate(R.id.fragmentMain)
    }
}