package com.example.exercise9.coroutines

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exercise9.R

class CoroutineViewModel(private val context: Context) : ViewModel(), TaskEventContract {

    private var task: CoroutineTask? = null

    private val _currentText = MutableLiveData<String>()
    val currentText: LiveData<String>
        get() = _currentText

    override fun onPreExecute() {
        _currentText.value = context.getString(R.string.task_created)
    }

    override fun onPostExecute() {
        _currentText.value = context.getString(R.string.done)
    }

    override fun onProgressUpdate(progress: Int) {
        _currentText.value = progress.toString()
    }

    override fun onCleared() {
        super.onCleared()
        task?.cancelTask()
    }

    fun onCreateClicked() {
        task =  CoroutineTask(this).apply {
            createTask()
        }
    }

    fun onStartClicked() {
        val started = task?.startTask()
        if (started == null || started == false) {
            _currentText.value = context.getString(R.string.click_create)
        }
    }

    fun onCancelClicked() {
        val canceled = task?.cancelTask()
        if (canceled == null) {
            _currentText.value = context.getString(R.string.no_task)
        } else {
            task = null
        }
    }
}