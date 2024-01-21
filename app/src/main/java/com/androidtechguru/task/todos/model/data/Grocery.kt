package com.androidtechguru.task.todos.model.data

import com.androidtechguru.task.todos.model.data.Category.Companion.REMINDER

data class Grocery(
    val id: Int
): Category(REMINDER)
