package com.androidtechguru.task.todos.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.ui.graphics.Color
import com.androidtechguru.task.todos.model.Priority
import com.androidtechguru.task.todos.model.data.BillPayment
import com.androidtechguru.task.todos.model.data.Category
import com.androidtechguru.task.todos.model.data.Reminder
import com.androidtechguru.task.todos.model.data.Task
import com.androidtechguru.task.todos.util.DummyTask.calendar
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


fun Task.setColor(): Color = when (priority) {
    Priority.LOW -> Color.Gray
    Priority.MEDIUM -> Color.Blue
    Priority.HIGH -> Color.Red
    Priority.BLOCKER -> Color.Red
}

fun Date.formattedDate(): String {
//    val formatter = SimpleDateFormat("EEEE, MMMM dd, yyyy", Locale.getDefault())
    val formatter = SimpleDateFormat("EEE, MMM dd", Locale.getDefault())
    // Format the date using the defined format
    return formatter.format(time)
}

fun launchActivity(context: Context, nextActivity: Class<out Activity>) {
    val intent = Intent(context, nextActivity)
    context.startActivity(intent)
}

fun main() {
    println(calendar.time.formattedDate())
}

object DummyTask {

    val calendar = Calendar.getInstance().apply {
        set(Calendar.YEAR, 2024)
        set(Calendar.MONTH, Calendar.JANUARY) // January is indexed as 0
        set(Calendar.DAY_OF_MONTH, 10)
    }

    // Get the Date object from the Calendar instance
    var date = calendar.time
    val formatter = SimpleDateFormat("EEEE, MMMM dd, yyyy", Locale.getDefault())

    // Format the date using the defined format
    val formattedDate = formatter.format(date)

    fun getAllList(): List<Task> {
        val taskList = mutableListOf<Task>()
        for (task in 1..10) {
            taskList.add(billPaymentTask(task))
            taskList.add(reminderTask(task))
            taskList.add(medicineTask())
        }
        return taskList
    }

    fun billPaymentTask(id: Int = 0) = Task(
        name = Category.BILLPAYMENT,
        time = 20,
        date = date,
        priority = Priority.HIGH,
        category = DummyBillPayment.bill(),
        tag = Category.BILLPAYMENT,
    )

    fun reminderTask(id: Int = 0) = Task(
        name = Category.REMINDER,
        time = 2,
        date = calendar.run {
            set(Calendar.DAY_OF_MONTH, 12)
            time
        },
        priority = Priority.MEDIUM,
        category = Reminder(id, date),
        tag = Category.REMINDER,
    )

    fun medicineTask(id: Int = 0) = Task(
        name = Category.MEDICINE,
        time = 2,
        date = calendar.run {
            set(Calendar.DAY_OF_MONTH, 8)
            time
        },
        priority = Priority.MEDIUM,
        category = Reminder(id, date),
        tag = Category.MEDICINE,
    )
}

object DummyBillPayment {
    fun bill() = BillPayment(
        amount = 200,
        dueDate = null,
        id = 20
    )
}
