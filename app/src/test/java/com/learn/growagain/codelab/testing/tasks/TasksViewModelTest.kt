package com.learn.growagain.codelab.testing.tasks

import com.learn.growagain.codelab.testing.data.FakeTaskRepository
import com.learn.growagain.codelab.testing.model.Task
import org.junit.Before
import org.junit.Test


class TasksViewModelTest {

    private lateinit var tasksViewModel: TasksViewModel
    private lateinit var tasksRepository: FakeTaskRepository

    @Before
    fun setup() {
         tasksRepository = FakeTaskRepository()
        val task1 = Task("Title1", "Description1")
        val task2 = Task("Title2", "Description2", isCompleted = true)
        val task3 = Task("Title3", "Description3", isCompleted = true)
        tasksRepository.addTasks(task1, task2, task3)
    }

    @Test
    fun addNewTask_setsNewTaskEvent() {
        
    }

}
