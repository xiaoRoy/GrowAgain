package com.learn.growagain.codelab.testing.data.source

import com.learn.growagain.codelab.testing.data.Result
import com.learn.growagain.codelab.testing.model.Task

interface ITaskRepository {
    suspend fun getAllTasks(forceUpdate: Boolean): Result<List<Task>>

    suspend fun reloadAllTasks()

    suspend fun saveTask(task: Task)

    suspend fun getTask(taskId: String, forceUpdate: Boolean = false): Result<Task>

}