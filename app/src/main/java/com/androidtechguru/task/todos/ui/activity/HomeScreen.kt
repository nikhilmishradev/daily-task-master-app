@file:OptIn(ExperimentalLayoutApi::class, ExperimentalLayoutApi::class)

package com.androidtechguru.task.todos.ui.activity

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.androidtechguru.task.todos.model.data.Task
import com.androidtechguru.task.todos.repository.TaskDao
import com.androidtechguru.task.todos.util.DummyTask
import com.androidtechguru.task.todos.util.launchActivity
import kotlinx.coroutines.launch
import java.util.Calendar


@Composable
fun StickyHeader(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray),
        color = Color.White
    )
}

val groupedItems: Map<String, List<Task>> = DummyTask.getAllList().groupBy { it.name }

@OptIn(ExperimentalLayoutApi::class)
//@Preview(showBackground = true)
@Composable
fun DefaultPreview(taskDao: TaskDao) {
    LazyColumn(
        modifier = Modifier
            .wrapContentSize()
            .padding(top = 12.dp, end = 5.dp)
    ) {
        item {
            UpcomingTask(taskDao)
            MultiItemCardUI(groupedItems, taskDao)
        }
    }
}

@Composable
fun UpcomingTask(taskDao: TaskDao) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val upcomingTaskList = DummyTask.getAllList()
        .filter { task ->
            task.date!! <= DummyTask.calendar.run {
                set(Calendar.DAY_OF_MONTH, 10)
                time
            }
        }
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = "Upcoming Task...",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            modifier = Modifier.clickable {
                launchActivity(
                    context,
                    UpcomingTaskActivity::class.java
                )
            }
        )
        IconButton(onClick = {
            coroutineScope.launch {
                deleteAllTasks(taskDao)
            }
        }) {
            Text(text = "Delete All")
        }
    }
    MultiItemCardUI(upcomingTaskList.groupBy { it.name }, taskDao)
}


@Composable
fun MultiItemCardUI(groupedItems: Map<String, List<Task>>, taskDao: TaskDao) {

    Column(
//        columns = GridCells.Fixed(2),
        modifier = Modifier
            .wrapContentSize()
            .padding(12.dp)
    ) {
        groupedItems.forEach { (header, items) ->
            StickyHeader(text = header)

            FlowColumn(
                maxItemsInEachColumn = 3,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                items.forEach { task ->
                    FlowRow(
                        maxItemsInEachRow = 2,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TaskCard(task = task, onClick = { _task ->
                            insertTask(_task, taskDao = taskDao)
                        })
                    }
                    Log.v("TAG", "Task: $task")
                }
            }
        }
    }
}

suspend fun insertTask(task: Task, taskDao: TaskDao) = taskDao.insertTask(task)

suspend fun getAllTasks(taskDao: TaskDao): List<Task> = taskDao.getAllTasks()

suspend fun deleteTask(task: Task, taskDao: TaskDao) = taskDao.delete(task)

suspend fun deleteTaskById(id: Int, taskDao: TaskDao) = taskDao.deleteTaskById(id)

suspend fun deleteAllTasks(taskDao: TaskDao) = taskDao.deleteAllTasks()

class RoomOperation() : TaskDao {
    override suspend fun insertTask(task: Task) {
        TODO("Not yet implemented")
    }

    override suspend fun updateTask(task: Task) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(task: Task) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTaskById(taskId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllTasks(): List<Task> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllTasks() {
        TODO("Not yet implemented")
    }

}