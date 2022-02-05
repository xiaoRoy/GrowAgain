package com.learn.growagain.codelab.espresso

import android.content.Context
import android.content.Intent

const val EXTRA_MESSAGE = "com.learn.growagain.codelab.espresso.extra.MESSAGE"
const val EXTRA_REPLY = "com.learn.growagain.codelab.espresso.extra.REPLY"

fun setupSecondEspressoIntent(context: Context, message: String): Intent =
    Intent(context, SecondEspressoActivity::class.java).apply {
        putExtra(EXTRA_MESSAGE, message)
    }

fun setupReplyIntent(reply: String): Intent = Intent().apply {
    putExtra(EXTRA_REPLY, reply)
}