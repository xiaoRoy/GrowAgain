package com.learn.growagain.codelab.testing.data

import com.learn.growagain.codelab.testing.data.source.FakeDataSource
import com.learn.growagain.codelab.testing.data.source.TaskRepository
import com.learn.growagain.codelab.testing.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class TaskRepositoryTest {
    private val task1 = Task("Title1", "Description1")
    private val task2 = Task("Title2", "Description2")
    private val task3 = Task("Title3", "Description3")
    private val remoteTasks = listOf(task1, task2).sortedBy { it.id }
    private val localTasks = listOf(task3).sortedBy { it.id }
    private val newTasks = listOf(task3).sortedBy { it.id }

    private lateinit var tasksRemoteDataSource: FakeDataSource
    private lateinit var tasksLocalDataSource: FakeDataSource

    private lateinit var tasksRepository: TaskRepository

    @Before
    fun setup() {
        tasksRemoteDataSource =
            FakeDataSource(
                remoteTasks.toMutableList()
            )
        tasksLocalDataSource =
            FakeDataSource(
                localTasks.toMutableList()
            )
        tasksRepository =
            TaskRepository(
                tasksRemoteDataSource, tasksLocalDataSource, Dispatchers.Unconfined
            )
    }

    @Test
    fun getTasks_requestsAllTasksFromRemoteDataSource() = runBlockingTest {
        val tasks = tasksRepository.getAllTasks(true) as Result.Success<List<Task>>
        assertEquals(tasks.data, remoteTasks)
    }
}