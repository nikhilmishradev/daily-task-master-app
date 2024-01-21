package com.androidtechguru.task.todos.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.androidtechguru.task.todos.model.data.Task
import com.androidtechguru.task.todos.model.room.TaskDatabase
import com.androidtechguru.task.todos.ui.activity.ui.theme.DailyTaskMasterProTheme
import kotlinx.coroutines.launch

class UpcomingTaskActivity : ComponentActivity() {
    private lateinit var taskDatabase: TaskDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        taskDatabase = TaskDatabase.getDatabase(applicationContext)
        val taskDao = taskDatabase.taskDao()

        setContent {
            DailyTaskMasterProTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var taskList: List<Task> by remember { mutableStateOf(emptyList()) }
                    LaunchedEffect("db") {
                        lifecycleScope.launch {
                            taskList = getAllTasks(taskDao)
                            Log.v("TAG_ROOM_DB", "Room DB: getAllTasks() -> ${taskList}")
                        }
                    }
                    LazyColumn {
                        item {
                            MultiItemCardUI(taskList.groupBy { it.name }, taskDao)
                        }
                    }
                }
            }
        }
    }
}
