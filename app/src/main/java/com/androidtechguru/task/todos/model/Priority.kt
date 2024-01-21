package com.androidtechguru.task.todos.model

enum class Priority(val value: Int = 1) {
    LOW(1),
    MEDIUM(2),
    HIGH(3),
    BLOCKER(4)
}