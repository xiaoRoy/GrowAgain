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

class TaskRepository(
    private val taskRemoteDataSource: TaskDataSource,
    private val taskLocalDataSource: TaskDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

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
            taskLocalDataSource.deleteAllTasks()
            saveTasksInLocal(tasksFromRemote.data)
        } else if (tasksFromRemote is Result.Error) {
            throw tasksFromRemote.exception
        }
    }

    private suspend fun saveTasksInLocal(tasksFromRemote: List<Task>) {
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

    companion object {

        @Volatile
        private var INSTANCE: TaskRepository? = null

        fun getInstance(application: Application): TaskRepository {
            return INSTANCE ?: synchronized(this) {
                val dataBase =
                    Room.databaseBuilder(application, TaskDataBase::class.java, "Tasks.db").build()
                return TaskRepository(
                    TaskRemoteDataSource,
                    TaskLocalDataSource(dataBase.taskDao())
                ).also {
                    INSTANCE = it
                }
            }
        }
    }


}