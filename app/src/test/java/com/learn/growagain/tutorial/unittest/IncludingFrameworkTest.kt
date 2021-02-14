package com.learn.growagain.tutorial.unittest

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import android.content.Context
import com.google.common.truth.Truth.assertThat
import com.learn.growagain.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class IncludingFrameworkTest {

    private lateinit var context: Context


    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun test_getStringRes() {
        val result = getCopyFromResource(context, R.string.hello_world)
        assertThat(result).isEqualTo("Hello World!")
    }
}