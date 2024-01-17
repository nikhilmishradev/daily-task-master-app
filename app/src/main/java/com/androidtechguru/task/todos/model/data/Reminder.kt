package com.androidtechguru.task.todos.model.data

import java.util.Date

data class Reminder(
    val id:Int=0,
    val date: Date?=null
): Category(REMINDER)
