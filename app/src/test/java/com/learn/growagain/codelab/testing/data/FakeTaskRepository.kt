package com.learn.growagain.codelab.testing.data

import com.learn.growagain.codelab.testing.data.source.ITaskRepository
import com.learn.growagain.codelab.testing.model.Task
import kotlinx.coroutines.runBlocking

class FakeTaskRepository: ITaskRepository {

    var taskMap: LinkedHashMap<String, Task> = LinkedHashMap()

    override suspend fun getAllTasks(forceUpdate: Boolean): Result<List<Task>> {
        return Result.Success(taskMap.values.toList())
    }

    override suspend fun reloadAllTasks() {
        TODO("not implemented")
    }

    override suspend fun saveTask(task: Task) {
        TODO("not implemented")
    }



    fun addTasks(vararg tasks: Task) {
        tasks.forEach {
            taskMap[it.id] = it
        }
        runBlocking { reloadAllTasks() }
    }
}