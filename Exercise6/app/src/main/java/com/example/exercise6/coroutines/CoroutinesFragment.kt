package com.example.exercise6.coroutines

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.exercise6.R
import com.example.exercise6.TaskEventContract
import kotlinx.android.synthetic.main.fragment_coroutines.*
import java.lang.Integer.parseInt

const val CURRENT_VAL = "CURRENT_VAL"

class CoroutinesFragment : Fragment(R.layout.fragment_coroutines) {

    private var coroutineTask: CoroutineTask? = null

    private val taskEventContract = object : TaskEventContract {
        override fun onPreExecute() {
            txtvTitle.text = getString(R.string.task_created)
        }
        override fun onPostExecute() {
            txtvTitle.text = getString(R.string.done)
        }
        override fun onProgressUpdate(progress: Int) {
            txtvTitle.text = progress.toString()
        }
        override fun onCancelled() {
            txtvTitle.text = getString(R.string.task_cancelled)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setOnClick()
        restoreState(savedInstanceState)
    }

    override fun onDestroy() {
        coroutineTask?.cancelTask(true)
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(CURRENT_VAL, txtvTitle.text.toString())
    }

    private fun setOnClick() {
        btnCreate.setOnClickListener { createTask() }
        btnStart.setOnClickListener { coroutineTask?.startTask() }
        btnCancel.setOnClickListener { coroutineTask?.cancelTask() }
    }

    private fun restoreState(savedState: Bundle?) {
        val currentVal = savedState?.getString(CURRENT_VAL)
        currentVal?.apply {
            txtvTitle.text = this
            try {
                restartTask(parseInt(this))
            } catch (e: NumberFormatException) {
                if (this == getString(R.string.task_created)) {
                    createTask()
                }
            }
        }
    }

    private fun createTask() {
        coroutineTask = CoroutineTask(taskEventContract)
            .apply { createTask() }
    }

    private fun restartTask(num: Int) {
        coroutineTask = CoroutineTask(taskEventContract).apply {
            savedPoint = num
            startTask()
        }
    }
}