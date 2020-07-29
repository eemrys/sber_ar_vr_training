package com.example.exercise11.ui

import android.Manifest
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.exercise11.IMAGE_PROGRESS
import com.example.exercise11.IMAGE_URL
import com.example.exercise11.PATH
import com.example.exercise11.R
import com.example.exercise11.service.DownloadReceiver
import com.example.exercise11.service.DownloadService
import kotlinx.android.synthetic.main.fragment_main.*
import java.io.File

private const val PERMISSION = Manifest.permission.WRITE_EXTERNAL_STORAGE
private const val PERMISSIONS_REQUEST_CODE = 1

class MainFragment : Fragment(R.layout.fragment_main) {

    private val downloadReceiver by lazy {
        DownloadReceiver()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        setObserver()
    }

    override fun onResume() {
        subscribeForProgressUpdates()
        super.onResume()
    }

    override fun onPause() {
        requireContext().unregisterReceiver(downloadReceiver)
        super.onPause()
    }

    private fun setListener() {
        btnFab.setOnClickListener {
            onFabClick()
        }
    }

    private fun setObserver() {
        downloadReceiver.filePath.observe(viewLifecycleOwner, Observer {
            navigateToImageFragment(it)
        })
    }

    private fun subscribeForProgressUpdates() {
        val filter = IntentFilter()
        filter.addAction(IMAGE_PROGRESS)
        requireContext().registerReceiver(downloadReceiver, filter)
    }

    private fun onFabClick() {
        if (isPermissionGranted()) {
            startDownloadService()
        } else {
            requestPermission()
        }
    }

    private fun navigateToImageFragment(path: String) {
        val args = ImageFragmentArgs.Builder(path).build().toBundle()
        findNavController().navigate(R.id.fragmentImage, args)
    }

    private fun isPermissionGranted(): Boolean =
        ContextCompat.checkSelfPermission(requireContext(), PERMISSION) ==
                PackageManager.PERMISSION_GRANTED

    private fun startDownloadService() {
        val intent = Intent(activity, DownloadService::class.java)
        val url = eTxtUrl.text.toString()
        val path: File = requireContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) ?: return
        intent.putExtra(PATH, path.absolutePath)
        intent.putExtra(IMAGE_URL, url)
        requireActivity().startService(intent)
    }

    private fun requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),
                PERMISSION
            )) {
            showExplainingRationaleDialog()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(PERMISSION),
                PERMISSIONS_REQUEST_CODE
            )
        }
    }

    private fun showExplainingRationaleDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.dialog_title)
            .setMessage(R.string.dialog_message)
            .setPositiveButton(R.string.dialog_ok) { _, _ ->
                ActivityCompat.requestPermissions(
                    requireActivity(), arrayOf(PERMISSION),
                    PERMISSIONS_REQUEST_CODE
                )
            }
            .create()
            .show()
    }
}