package com.example.exercise6.coroutines

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.exercise6.R
import com.example.exercise6.TaskEventContract
import kotlinx.android.synthetic.main.fragment_coroutines.*

class CoroutinesFragment : Fragment(R.layout.fragment_coroutines),
    TaskEventContract {

    private var coroutineTask: CoroutineTask? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val currentPoint = savedInstanceState?.getInt("CURRENT_VAL") ?: 0
        setOnClick(currentPoint)
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

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("CURRENT_VAL", coroutineTask?.currentPoint ?: 0)
    }

    private fun setOnClick(currentPoint: Int) {
        btnCreate.setOnClickListener {
            coroutineTask = CoroutineTask(this, currentPoint)
                .apply { createTask() }

        }
        btnStart.setOnClickListener { coroutineTask?.startTask() }
        btnCancel.setOnClickListener { coroutineTask?.cancelTask() }
    }
}