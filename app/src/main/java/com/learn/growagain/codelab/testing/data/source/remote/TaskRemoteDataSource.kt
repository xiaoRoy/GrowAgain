package com.learn.growagain.codelab.testing.data.source.remote

import com.learn.growagain.codelab.testing.data.Result
import com.learn.growagain.codelab.testing.data.source.TaskDataSource
import com.learn.growagain.codelab.testing.model.Task
import kotlinx.coroutines.delay

object TaskRemoteDataSource: TaskDataSource {

    private const val SERVICE_DELAY = 2000L

    private var TASKS = LinkedHashMap<String, Task>(2)

    init {
        addTask("Running", "Run every day!")
        addTask("Coding", "Code every day!")
    }


    override suspend fun saveTask(task: Task) {
        TASKS[task.id] = task
    }

    override suspend fun getTasks(): Result<List<Task>> {
        val taskList = TASKS.values.toList()
        delay(SERVICE_DELAY)
        return Result.Success(taskList)
    }

    override suspend fun deleteAllTasks() {
        TODO("not implemented")
    }

    private fun addTask(title: String, description: String) {
        val task = Task(title, description)
        TASKS[task.id] = task
    }
}