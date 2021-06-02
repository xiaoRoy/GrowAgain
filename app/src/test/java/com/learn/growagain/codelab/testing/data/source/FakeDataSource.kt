package com.learn.growagain.codelab.testing.data.source

import com.learn.growagain.codelab.testing.data.Result
import com.learn.growagain.codelab.testing.model.Task
import org.junit.Assert.*
import java.lang.IllegalStateException

class FakeDataSource(var tasks: MutableList<Task>?) : TaskDataSource {
    override suspend fun getTasks(): Result<List<Task>> {
        val tempTasks = tasks
        return if (tempTasks != null) {
            Result.Success(tempTasks)
        } else {
            Result.Error(IllegalStateException("Tasks not found"))
        }
    }

    override suspend fun saveTask(task: Task) {
        tasks?.add(task)
    }

    override suspend fun deleteAllTasks() {
        tasks?.clear()
    }
}