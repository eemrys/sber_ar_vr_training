package com.example.exercise6.threads

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.exercise6.R
import com.example.exercise6.TaskEventContract
import kotlinx.android.synthetic.main.fragment_threads_handler.*

class ThreadsHandlerFragment : Fragment(R.layout.fragment_threads_handler), TaskEventContract {

    private var asyncTask: CounterAsyncTask? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setOnClick()
    }

    override fun onDestroy() {
        asyncTask?.apply {
            cancel()
            asyncTask = null
        }
        super.onDestroy()
    }

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

    private fun setOnClick() {
        btnCreate.setOnClickListener {
            asyncTask = CounterAsyncTask(this)
        }
        btnStart.setOnClickListener { asyncTask?.execute() }
        btnCancel.setOnClickListener { asyncTask?.cancel() }
    }
}