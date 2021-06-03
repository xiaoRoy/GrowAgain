package com.learn.growagain.codelab.testing.tasks

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.learn.growagain.codelab.testing.data.source.TaskRepository
import com.learn.growagain.codelab.testing.viewmodelcommon.TaskViewModeFactory

class TasksFragment: Fragment() {
    private val  viewModel by viewModels<TasksViewModel> {
        TaskViewModeFactory(TaskRepository.getInstance(requireActivity().application))
    }


}