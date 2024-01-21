package com.androidtechguru.task.todos.model.data

import com.androidtechguru.task.todos.model.data.Category.Companion.MEDICINE

data class Medicine(
    val id:Int
): Category(MEDICINE)
