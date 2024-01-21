package com.androidtechguru.task.todos.model.room

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {
    @TypeConverter
    fun fromDate(date: Date?): Long? = date?.time

    @TypeConverter
    fun toDate(timeStamp: Long?): Date? = timeStamp?.let { Date(it) }
}