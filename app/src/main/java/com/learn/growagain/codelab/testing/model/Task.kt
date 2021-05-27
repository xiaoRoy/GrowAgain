package com.learn.growagain.codelab.testing.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "tasks")
class Task(
    private var title: String,
    private var description: String,
    @PrimaryKey @ColumnInfo var id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "completed") private var isCompleted: Boolean = false
) {}