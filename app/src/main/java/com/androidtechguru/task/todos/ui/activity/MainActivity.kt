package com.androidtechguru.task.todos.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.androidtechguru.task.todos.model.room.TaskDatabase
import com.androidtechguru.task.todos.ui.theme.DailyTaskMasterProTheme

class MainActivity : ComponentActivity() {
    private lateinit var taskDatabase : TaskDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        taskDatabase= TaskDatabase.getDatabase(applicationContext)
        val taskDao= taskDatabase.taskDao()

        setContent {
            DailyTaskMasterProTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DefaultPreview(taskDao)
                }
            }
        }
    }
}



