package com.learn.growagain.tutorial.unittest.instrument

import android.os.Parcel
import android.os.Parcelable
import java.lang.IllegalArgumentException

class LogHistory() : Parcelable {
    private val data = mutableListOf<Pair<String, Long>>()

    constructor(parcel: Parcel) : this() {

        with(parcel) {
            val size = readInt()
            val texts = arrayOfNulls<String>(size)
            val timestamps = LongArray(size)
            readStringArray(texts)
            readLongArray(timestamps)

            if (texts.size != timestamps.size) {
                throw IllegalArgumentException("Error reading saved state.")
            }
            data.clear()
            for (index in texts.indices) {
                val text = texts[index]
                if (text != null) {
                    data.add(text to timestamps[index])
                }
            }
        }
    }

    fun addEntry(text: String, timestamp: Long) {
        data.add(text to timestamp)
    }

    fun getAllData() = listOf(*data.toTypedArray())

    override fun writeToParcel(parcel: Parcel, flags: Int) {

        val size = data.size
        val texts = arrayOfNulls<String>(size)
        val timestamps = LongArray(size)
        data.forEachIndexed { index, item ->
            texts[index] = item.first
            timestamps[index] = item.second
        }
        with(parcel) {
            writeInt(size)
            writeStringArray(texts)
            writeLongArray(timestamps)
        }

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LogHistory> {
        override fun createFromParcel(parcel: Parcel): LogHistory {
            return LogHistory(parcel)
        }

        override fun newArray(size: Int): Array<LogHistory?> {
            return arrayOfNulls(size)
        }
    }

}