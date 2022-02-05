package com.learn.growagain.codelab.espresso

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.learn.growagain.R
import java.lang.IllegalArgumentException

class SecondEspressoActivity : AppCompatActivity() {

    private lateinit var etReply: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val message: String = intent?.getStringExtra(EXTRA_MESSAGE) ?: throw IllegalArgumentException()
        setContentView(R.layout.activity_second_espresso)

        val tvMessage = findViewById<TextView>(R.id.text_message)
        tvMessage.text = message
        etReply = findViewById(R.id.editText_reply)

        findViewById<View>(R.id.button_reply).setOnClickListener {
            val replyMessage = etReply.text.toString()
            val replyIntent = setupReplyIntent(replyMessage)
            setResult(RESULT_OK, replyIntent)
            finish()
        }

    }
}