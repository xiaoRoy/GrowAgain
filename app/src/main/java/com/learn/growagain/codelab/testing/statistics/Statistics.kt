package com.learn.growagain.codelab.testing.statistics

import com.learn.growagain.codelab.testing.model.Task
import java.lang.IllegalArgumentException


internal fun getActiveAndCompletedStats(tasks: List<Task>?): Statistics {
    return if (tasks.isNullOrEmpty()) {
        Statistics(0f, 0f)
    } else {
        val numberOfTotalTasks = tasks.size
        val numberOfActiveTasks = tasks.count { it.isActive }
        val activeTasksPercent = 100f * numberOfActiveTasks / numberOfTotalTasks
        val completedTasksPercent =
            100f * (numberOfTotalTasks - numberOfActiveTasks) / numberOfTotalTasks
        Statistics(activeTasksPercent, completedTasksPercent)
    }
}

class Statistics(
    val activeTasksPercent: Float,
    val completedTasksPercent: Float
)