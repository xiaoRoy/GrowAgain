package com.learn.growagain.codelab.testing.taskdetail

import android.os.Bundle
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.learn.growagain.R
import com.learn.growagain.codelab.testing.model.Task
import com.learn.growagain.codelab.testing.taskdetail.TaskDetailFragment.Companion.BUNDLE_KEY_TASK_ID
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class TaskDetailFragmentTest {


    @Test
    fun showTaskDetail_notCompleted_shouldNotChecked() {
        val activeTask = Task("coding", "coding every day!", isCompleted = false)
        val bundle = Bundle().apply {
            putString(BUNDLE_KEY_TASK_ID, activeTask.id)
        }

        launchFragmentInContainer<TaskDetailFragment>(bundle, R.style.Theme_GrowAgain)

        Thread.sleep(5000)

    }
}