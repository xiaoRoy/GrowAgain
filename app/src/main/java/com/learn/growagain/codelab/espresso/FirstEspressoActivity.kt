package com.learn.growagain.codelab.espresso

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.learn.growagain.R

class FirstEspressoActivity : AppCompatActivity() {

    private lateinit var viewHeaderReplied: View
    private lateinit var tvMessageReplied: TextView
    private lateinit var etSendMessage: EditText
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_espresso)

        viewHeaderReplied = findViewById(R.id.text_header_replied)
        tvMessageReplied = findViewById(R.id.text_message_replied)
        etSendMessage = findViewById(R.id.editText_send)
        initActivityResultLauncher()


        findViewById<View>(R.id.button_send).setOnClickListener {
            val message = etSendMessage.text.toString()
//            startActivityForResult(setupSecondEspressoIntent(this, message))
            activityResultLauncher.launch(setupSecondEspressoIntent(this, message))
        }
    }

    private fun initActivityResultLauncher() {
        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (RESULT_OK == result.resultCode) {
                    val repliedMessage = result.data?.getStringExtra(EXTRA_REPLY)
                    if (repliedMessage != null) {
                        viewHeaderReplied.visibility = View.VISIBLE
                        tvMessageReplied.visibility = View.VISIBLE
                        tvMessageReplied.text = repliedMessage
                    }
                }
            }
    }
}