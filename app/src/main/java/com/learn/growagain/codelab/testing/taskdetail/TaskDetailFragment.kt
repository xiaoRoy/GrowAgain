package com.learn.growagain.codelab.testing.taskdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.learn.growagain.R
import com.learn.growagain.codelab.testing.data.source.TaskRepository
import com.learn.growagain.codelab.testing.viewmodelcommon.EventObserver
import com.learn.growagain.codelab.testing.viewmodelcommon.TaskViewModeFactory
import java.lang.IllegalArgumentException

class TaskDetailFragment : Fragment() {
    private val viewModel by viewModels<TaskDetailViewModel> {
        TaskViewModeFactory(TaskRepository.getInstance(this.requireActivity().application))
    }

    private lateinit var taskId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        taskId = arguments?.getString(BUNDLE_KEY_TASK_ID) ?: throw IllegalArgumentException()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_task_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutNoTasks: View = view.findViewById(R.id.fl_task_detail_no_tasks)
        val tvNoTasks: View = view.findViewById(R.id.tv_task_detail_no_task)

        val layoutTask: View = view.findViewById(R.id.constraint_task_detail)
        val checkBoxCompleted: CheckBox = view.findViewById(R.id.checkbox_task_detail_completed)
        val tvTaskTitle: TextView = view.findViewById(R.id.tv_task_detail_task_title)
        val tvTaskDescription: TextView = view.findViewById(R.id.tv_task_detail_description)

        viewModel.task.observe(viewLifecycleOwner, Observer { task ->
            task?.let {
                checkBoxCompleted.isChecked = it.isCompleted
                tvTaskTitle.text = it.title
                tvTaskDescription.text = it.description
            }
        })

        viewModel.hasTask.observe(viewLifecycleOwner, Observer { hasTask ->
            if (hasTask) {
                layoutNoTasks.visibility = View.GONE
                layoutTask.visibility = View.VISIBLE
            } else {
                layoutNoTasks.visibility = View.VISIBLE
                layoutTask.visibility = View.GONE
            }
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            tvNoTasks.visibility = if (isLoading) View.GONE else View.VISIBLE
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, EventObserver {
            Snackbar.make(view, it, Snackbar.LENGTH_LONG).show()
        })

        viewModel.showTaskDetail(taskId)
    }

    companion object {

        @VisibleForTesting
        const val BUNDLE_KEY_TASK_ID = "bundle_key_task_id"

        fun getInstance(taskId: String): TaskDetailFragment {
            return TaskDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(BUNDLE_KEY_TASK_ID, taskId)
                }
            }
        }
    }

}