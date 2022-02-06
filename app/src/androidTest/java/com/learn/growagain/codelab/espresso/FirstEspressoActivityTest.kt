package com.learn.growagain.codelab.espresso

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.learn.growagain.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FirstEspressoActivityTest {

    @get:Rule
    val firstEspressoActivityRule: ActivityTestRule<FirstEspressoActivity> =
        ActivityTestRule(FirstEspressoActivity::class.java)

    @Test
    fun test_sendEmptyMessage_success() {
        onView(withId(R.id.button_send)).perform(click())
        onView(withId(R.id.text_header)).check(matches(isDisplayed()))

        onView(withId(R.id.button_reply)).perform(click())
        onView(withId(R.id.text_header_replied)).check(matches(isDisplayed()))
    }

    @Test
    fun test_inputMessage_success() {
        val message = "This is a test."
        onView(withId(R.id.editText_send)).perform(typeText(message))
        onView(withId(R.id.button_send)).perform(click())
        onView(withId(R.id.text_message)).check(matches(withText(message)))
    }
}