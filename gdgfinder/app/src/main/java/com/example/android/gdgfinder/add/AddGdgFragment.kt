package com.example.android.gdgfinder.add

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import com.example.android.gdgfinder.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_add_gdg.*

class AddGdgFragment : Fragment(R.layout.fragment_add_gdg) {

    private val viewModel: AddGdgViewModel by lazy {
        ViewModelProvider(this).get(AddGdgViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setOnClick()
        setObserver()
    }

    private fun setOnClick() {
        btnOk.setOnClickListener {
            viewModel.onSubmitApplication()
        }
    }

    private fun setObserver() {
        viewModel.showSnackBarEvent.observe(viewLifecycleOwner, Observer {
                it?.apply {
                    Snackbar.make(requireActivity().findViewById(android.R.id.content),
                        getString(R.string.application_submitted),
                        Snackbar.LENGTH_SHORT
                    ).show()
                    viewModel.doneShowingSnackbar()
                }
            })
    }
}