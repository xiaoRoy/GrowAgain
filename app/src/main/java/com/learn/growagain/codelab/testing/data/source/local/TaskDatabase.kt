package com.learn.growagain.codelab.testing.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.learn.growagain.codelab.testing.model.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase: RoomDatabase() {

    abstract fun taskDao(): TaskDao
}