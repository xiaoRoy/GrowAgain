package com.learn.growagain.codelab.testing.taskdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.learn.growagain.codelab.testing.data.source.ITaskRepository

class TaskDetailViewModel(private val taskRepository: ITaskRepository): ViewModel() {


    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading

    private val _taskId = MutableLiveData<String>()

    fun showTaskDetail(taskId: String) {

    }
}