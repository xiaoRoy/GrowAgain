package com.learn.growagain.codelab.testing.data.source

import com.learn.growagain.codelab.testing.data.Result
import com.learn.growagain.codelab.testing.model.Task

interface TaskDataSource {

    suspend fun getTasks(): Result<List<Task>>

    suspend fun saveTask(task: Task)

    suspend fun deleteAllTasks()
}