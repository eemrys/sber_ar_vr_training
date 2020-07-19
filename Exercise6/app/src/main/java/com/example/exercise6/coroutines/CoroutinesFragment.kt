package com.example.exercise6.coroutines

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.exercise6.R
import kotlinx.android.synthetic.main.fragment_coroutines.*

class CoroutinesFragment : Fragment(R.layout.fragment_coroutines),
    TaskEventContract {

    private var coroutineTask: CoroutineTask? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setOnClick()
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineTask?.cancelTask()
    }

    override fun onPreExecute() {
        txtvTitle.text = getString(R.string.task_created)
    }

    override fun onPostExecute() {
        txtvTitle.text = getString(R.string.done)
    }

    override fun onProgressUpdate(progress: Int) {
        txtvTitle.text = progress.toString()
    }

    private fun setOnClick() {
        btnCreate.setOnClickListener {
            coroutineTask = CoroutineTask(this)
                .apply { createTask() }
        }
        btnStart.setOnClickListener { coroutineTask?.startTask() }
        btnCancel.setOnClickListener { coroutineTask?.cancelTask() }
    }
}