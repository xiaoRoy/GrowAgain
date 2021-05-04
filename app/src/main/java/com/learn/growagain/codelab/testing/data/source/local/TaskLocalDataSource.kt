package com.learn.growagain.codelab.testing.data.source.local

import com.learn.growagain.codelab.testing.data.Result
import com.learn.growagain.codelab.testing.data.Result.Success
import com.learn.growagain.codelab.testing.data.Result.Error
import com.learn.growagain.codelab.testing.data.source.TaskDataSource
import com.learn.growagain.codelab.testing.model.Task
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class TaskLocalDataSource(
    private val taskDao: TaskDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : TaskDataSource {

    override suspend fun getTasks(): Result<List<Task>> = withContext(ioDispatcher) {
        try {
            Success(taskDao.getTasks())
        } catch (exception: Exception) {
            Error(exception)
        }
    }


    override suspend fun saveTask(task: Task) {
        withContext(ioDispatcher) {
            taskDao.insertTask(task)
        }
    }
}