package com.learn.growagain.tutorial.unittest.instrument

import android.os.Parcel
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LogHistoryTest {

    private lateinit var logHistory: LogHistory

    @Before
    fun setup() {
        logHistory = LogHistory()
    }

    @Test
    fun test_logHistory() {
        val parcel = Parcel.obtain()

        logHistory.apply {
            addEntry(TEST_STRING, TEST_LONG)
            writeToParcel(parcel, describeContents())
        }

        parcel.setDataPosition(0)

        val logHistoryFromParcel = LogHistory.createFromParcel(parcel)
        logHistoryFromParcel.getAllData().run {
            assertThat(size).isEqualTo(1)
            val onlyItem = first()
            assertThat(onlyItem.first).isEqualTo(TEST_STRING)
            assertThat(onlyItem.second).isEqualTo(TEST_LONG)
        }
    }

    companion object {
        const val TEST_STRING = "This is a string"
        const val TEST_LONG = 12345678L
    }
}