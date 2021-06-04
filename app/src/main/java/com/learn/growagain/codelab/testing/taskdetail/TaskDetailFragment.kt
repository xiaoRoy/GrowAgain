package com.learn.growagain.codelab.testing.taskdetail

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.learn.growagain.codelab.testing.data.source.TaskRepository
import com.learn.growagain.codelab.testing.viewmodelcommon.TaskViewModeFactory

class TaskDetailFragment : Fragment() {
    private val viewModel by viewModels<TaskDetailViewModel> {
        TaskViewModeFactory(TaskRepository.getInstance(this.requireActivity().application))
    }

}