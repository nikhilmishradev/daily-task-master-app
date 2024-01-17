package com.androidtechguru.task.todos.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.androidtechguru.task.todos.model.Priority
import com.androidtechguru.task.todos.model.room.CategoryConverter
import java.util.Date

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val time: Long,
    val date: Date?,
    val priority: Priority,
    val tag: String = "",
    @param:TypeConverters(CategoryConverter::class)
    val category: Category
)