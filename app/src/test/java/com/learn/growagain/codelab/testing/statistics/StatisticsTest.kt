package com.learn.growagain.codelab.testing.statistics

import com.learn.growagain.codelab.testing.model.Task
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.*
import org.junit.Test

class StatisticsTest {

    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsHundredZero() {
        val tasks = listOf(Task("Running", "Keep running.", isCompleted = false))
        val result = getActiveAndCompletedStats(tasks)
        assertEquals(0f, result.completedTasksPercent)
        assertEquals(100f, result.activeTasksPercent)
    }

    @Test
    fun getActiveAndCompletedStats_empty_returnsZeros() {
        val result = getActiveAndCompletedStats(emptyList())
        assertEquals(0f, result.completedTasksPercent)
        assertEquals(0f, result.activeTasksPercent)
    }
}