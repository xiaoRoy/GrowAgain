package com.learn.growagain.codelab.coroutine

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.learn.growagain.R

class CoroutineActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)

        val rootView = findViewById<View>(R.id.rootLayout)
        val tvTitle = findViewById<TextView>(R.id.title)
        val tvTaps = findViewById<TextView>(R.id.taps)
        val spinner = findViewById<ProgressBar>(R.id.spinner)

        val database = getDatabase(this)
        val titleRepository = TitleRepository(getTitleService(), database.titleDao)

        val viewModel = ViewModelProviders.of(this, TitleViewModel.FACTORY(titleRepository))
            .get(TitleViewModel::class.java)

        rootView.setOnClickListener {
            viewModel.updateTitle()
        }

        with(viewModel) {
            val lifecycleOwner = this@CoroutineActivity
            title.observe(lifecycleOwner, Observer { title ->
                title?.let {
                    tvTitle.text = it
                }
            })

            taps.observe(lifecycleOwner, Observer { taps ->
                tvTaps.text = taps
            })

            isLoading.observe(lifecycleOwner, Observer { isLoading ->
                spinner.visibility = if(isLoading) View.VISIBLE else View.GONE
            })

            message.observe(lifecycleOwner, Observer { message ->
                message?.let {
                    Snackbar.make(rootView, it, Snackbar.LENGTH_SHORT).show()
                    onMessageShown()
                }
            })
        }
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, CoroutineActivity::class.java))
        }
    }
}