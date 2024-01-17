package com.androidtechguru.task.todos.model.data

import java.util.Date

data class BillPayment(
    val amount: Int=0,
    val dueDate: Date?=null,
    val isPaid: Boolean=false,
    val id: Int=0,
    val name: String = "Bill Payment"
) : Category(BILLPAYMENT)
