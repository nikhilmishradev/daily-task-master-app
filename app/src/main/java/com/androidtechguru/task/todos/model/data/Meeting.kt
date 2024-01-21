package com.androidtechguru.task.todos.model.data

import com.androidtechguru.task.todos.model.data.Category.Companion.MEETING

data class Meeting(
    val id: Int
): Category(MEETING)
