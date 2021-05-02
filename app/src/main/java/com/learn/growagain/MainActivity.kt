package com.learn.growagain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.learn.growagain.codelab.coroutine.CoroutineActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CoroutineActivity.start(this)
    }
}