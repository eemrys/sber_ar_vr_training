package com.example.exercise6.threads

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.exercise6.R
import com.example.exercise6.TaskEventContract
import kotlinx.android.synthetic.main.fragment_threads_handler.*

const val CURRENT_VAL = "CURRENT_VAL"

class ThreadsHandlerFragment : Fragment(R.layout.fragment_threads_handler) {

    private var asyncTask: CounterAsyncTask? = null

    private val taskEventContract = object : TaskEventContract {
        override fun onPreExecute() {
            txtvTitle.text = getString(R.string.task_created)
        }
        override fun onPostExecute() {
            txtvTitle.text = getString(R.string.done)
            asyncTask = null
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
        asyncTask?.cancel(true)
        asyncTask = null
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(CURRENT_VAL, txtvTitle.text.toString())
    }

    private fun setOnClick() {
        btnCreate.setOnClickListener { createTask() }
        btnStart.setOnClickListener { asyncTask?.execute() }
        btnCancel.setOnClickListener { asyncTask?.cancel() }
    }

    private fun restoreState(savedState: Bundle?) {
        val currentVal = savedState?.getString(CURRENT_VAL)
        currentVal?.apply {
            txtvTitle.text = this
            try {
                restartTask(Integer.parseInt(this))
            } catch (e: NumberFormatException) {
                if (this == getString(R.string.task_created)) {
                    createTask()
                }
            }
        }
    }

    private fun createTask() {
        asyncTask = CounterAsyncTask(taskEventContract)
    }

    private fun restartTask(num: Int) {
        asyncTask = CounterAsyncTask(taskEventContract).apply {
            savedPoint = num
            execute()
        }
    }
}