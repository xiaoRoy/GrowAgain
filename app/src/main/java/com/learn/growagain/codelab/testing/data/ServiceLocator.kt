package com.learn.growagain.codelab.testing.data

import android.content.Context
import androidx.room.Room
import com.learn.growagain.codelab.testing.data.source.ITaskRepository
import com.learn.growagain.codelab.testing.data.source.TaskDataSource
import com.learn.growagain.codelab.testing.data.source.TaskRepository
import com.learn.growagain.codelab.testing.data.source.local.TaskDatabase
import com.learn.growagain.codelab.testing.data.source.local.TaskLocalDataSource
import com.learn.growagain.codelab.testing.data.source.remote.TaskRemoteDataSource

object ServiceLocator {

    private var database: TaskDatabase? = null

    @Volatile
    var taskRepository: ITaskRepository? = null


    fun provideTaskRepository(context: Context): ITaskRepository {
        synchronized(this) {
            return taskRepository ?:createTaskRepository(context)
        }
    }

    private fun createTaskRepository(context: Context): ITaskRepository {
        val taskRepo = TaskRepository(TaskRemoteDataSource, createTaskLocalDataSource(context))
        this.taskRepository = taskRepo
        return taskRepo
    }

    private fun createTaskLocalDataSource(context: Context): TaskDataSource {
        val database = database ?: createDatabase(context)
        return TaskLocalDataSource(database.taskDao())
    }

    private fun createDatabase(context: Context): TaskDatabase {
        val result = Room.databaseBuilder(
            context.applicationContext,
            TaskDatabase::class.java,
            "Tasks.db").build()
        database = result
        return result
    }

}