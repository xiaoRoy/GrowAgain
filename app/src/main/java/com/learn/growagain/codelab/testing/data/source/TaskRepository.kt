package com.learn.growagain.codelab.testing.data.source

import android.app.Application
import androidx.room.Room
import com.learn.growagain.codelab.testing.data.Result
import com.learn.growagain.codelab.testing.data.source.local.TaskDataBase
import com.learn.growagain.codelab.testing.data.source.local.TaskLocalDataSource
import com.learn.growagain.codelab.testing.data.source.remote.TaskRemoteDataSource
import com.learn.growagain.codelab.testing.model.Task
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.lang.Exception

class TaskRepository private constructor(application: Application) {

    private val taskRemoteDataSource: TaskDataSource
    private val taskLocalDataSource: TaskDataSource
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    init {
        val database = Room.databaseBuilder(
            application.applicationContext,
            TaskDataBase::class.java,
            "Tasks.db"
        ).build()

        taskRemoteDataSource = TaskRemoteDataSource
        taskLocalDataSource = TaskLocalDataSource(database.taskDao())
    }

    suspend fun getAllTasks(forceUpdate: Boolean): Result<List<Task>> {
        if (forceUpdate) {
            try {
                saveAllTasksToLocalFromRemote()
            } catch (exception: Exception) {
                return Result.Error(exception)
            }
        }
        return taskLocalDataSource.getTasks()
    }

    private suspend fun saveAllTasksToLocalFromRemote() {
        val tasksFromRemote = taskRemoteDataSource.getTasks()
        if (tasksFromRemote is Result.Success) {
            saveTaskInLocal(tasksFromRemote.data)
        } else if (tasksFromRemote is Result.Error) {
            throw tasksFromRemote.exception
        }
    }

    private suspend fun saveTaskInLocal(tasksFromRemote: List<Task>) {
        tasksFromRemote.forEach {
            taskLocalDataSource.saveTask(it)
        }
    }

    suspend fun reloadAllTasks() {
        saveAllTasksToLocalFromRemote()
    }

    suspend fun saveTask(task: Task) {
        coroutineScope {
            //if the first task fails, the second one will be cancelled
            //exception thrown from launch is consider as a uncaught exception
            launch { taskRemoteDataSource.saveTask(task) }
            launch { taskLocalDataSource.saveTask(task) }
        }
    }

    private suspend fun saveSingleTaskToLocalFromRemote(taskId: String) {
    }


}