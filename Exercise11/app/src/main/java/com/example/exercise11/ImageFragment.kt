package com.example.exercise11

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_image.*
import java.io.File

const val IMAGE_URL = "URL"
const val PATH = "PATH"
private const val PERMISSION = Manifest.permission.WRITE_EXTERNAL_STORAGE
private const val PERMISSIONS_REQUEST_CODE = 1

class ImageFragment : Fragment(R.layout.fragment_image) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
    }

    private fun setListener() {
        btnFab.setOnClickListener {
            onFabClick()
        }
    }

    private fun onFabClick() {
        if (isPermissionGranted()) {
            startDownloadService()
        } else {
            requestPermission()
        }
    }

    private fun isPermissionGranted(): Boolean =
        ContextCompat.checkSelfPermission(requireContext(), PERMISSION) ==
                PackageManager.PERMISSION_GRANTED

    private fun startDownloadService() {
        val url = eTxtUrl.text
        val intent = Intent(activity, DownloadService::class.java)
        val path: File? = requireContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
        path?.apply {
            intent.putExtra(PATH, this.absolutePath)
            intent.putExtra(IMAGE_URL, url)
            requireActivity().startService(intent)
            //activity?.onBackPressed()
        }
    }

    private fun requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), PERMISSION)) {
            showExplainingRationaleDialog()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(PERMISSION), PERMISSIONS_REQUEST_CODE)
        }
    }

    private fun showExplainingRationaleDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.dialog_title)
            .setMessage(R.string.dialog_message)
            .setPositiveButton(R.string.dialog_ok) {_, _ ->
                ActivityCompat.requestPermissions(
                    requireActivity(), arrayOf(PERMISSION), PERMISSIONS_REQUEST_CODE)
            }
            .create()
            .show()
    }
}