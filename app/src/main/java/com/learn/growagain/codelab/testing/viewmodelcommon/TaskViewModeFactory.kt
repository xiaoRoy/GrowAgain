package com.learn.growagain.codelab.testing.viewmodelcommon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.learn.growagain.codelab.testing.data.source.ITaskRepository
import com.learn.growagain.codelab.testing.taskdetail.TaskDetailViewModel
import com.learn.growagain.codelab.testing.tasks.TasksViewModel
import java.lang.IllegalArgumentException

class TaskViewModeFactory(
    private val taskRepository: ITaskRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(TasksViewModel::class.java) -> TasksViewModel(taskRepository)
            modelClass.isAssignableFrom(TaskDetailViewModel::class.java) ->
                TaskDetailViewModel(taskRepository)
            else -> throw IllegalArgumentException()
        } as T
    }
}