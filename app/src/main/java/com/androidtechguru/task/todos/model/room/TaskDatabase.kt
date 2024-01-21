package com.androidtechguru.task.todos.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.androidtechguru.task.todos.model.data.Task
import com.androidtechguru.task.todos.repository.TaskDao

@Database(entities = [Task::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class,CategoryConverter::class)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: TaskDatabase? = null

        fun getDatabase(context: Context): TaskDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "task_database"
                )
                    .addTypeConverter(CategoryConverter())
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }


}