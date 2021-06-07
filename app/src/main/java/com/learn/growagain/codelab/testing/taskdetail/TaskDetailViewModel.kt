package com.learn.growagain.codelab.testing.taskdetail

import androidx.annotation.StringRes
import androidx.lifecycle.*
import com.learn.growagain.R
import com.learn.growagain.codelab.testing.data.Result
import com.learn.growagain.codelab.testing.data.source.ITaskRepository
import com.learn.growagain.codelab.testing.model.Task
import com.learn.growagain.codelab.testing.viewmodelcommon.Event
import kotlinx.coroutines.launch

class TaskDetailViewModel(private val taskRepository: ITaskRepository) : ViewModel() {


    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _task = MutableLiveData<Task?>()
    val task: LiveData<Task?> = _task

    val hasTask: LiveData<Boolean> = _task.map { it != null }

    private val _errorMessage = MutableLiveData<Event<Int>>()
    val errorMessage: LiveData<Event<Int>> = _errorMessage


    fun showTaskDetail(taskId: String) {
        if (_isLoading.value == true || _task.value?.id == taskId) return
        viewModelScope.launch {
            val taskResult = taskRepository.getTask(taskId)
            if (taskResult is Result.Success) {
                val task = taskResult.data
                _task.value = task
            } else {
                _task.value = null
                showErrorMessage(R.string.loading_task_error)
            }
        }
    }

    private fun showErrorMessage(@StringRes message: Int) {
        _errorMessage.value = Event(message)
    }
}