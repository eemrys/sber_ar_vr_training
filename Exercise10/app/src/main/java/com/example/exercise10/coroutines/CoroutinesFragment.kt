package com.example.exercise10.coroutines

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.exercise10.R
import kotlinx.android.synthetic.main.fragment_coroutines.*

class CoroutinesFragment : Fragment(R.layout.fragment_coroutines) {

    private val viewModelFactory by lazy {
        CoroutineViewModelFactory(requireContext())
    }
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(CoroutineViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setOnClick()
        setObservers()
    }

    private fun setOnClick() {
        viewModel.apply {
            btnCreate.setOnClickListener { onCreateClicked() }
            btnStart.setOnClickListener { onStartClicked() }
            btnCancel.setOnClickListener { onCancelClicked() }
        }
    }

    private fun setObservers() {
        viewModel.currentText.observe(viewLifecycleOwner, Observer {
            txtvTitle.text = it
        })
    }
}