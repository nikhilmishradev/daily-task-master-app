package com.androidtechguru.task.todos.model.data


open class CategoryType(
    val name: String = ""
)

//sealed class Category(tag: String)

fun test() {
}

open class Category(tag: String) {
//    data class BillPayment(val id:Int): Category()

    companion object {
        const val CATEGORY = "Category"
        const val GENERAL = "General"
        const val BILLPAYMENT = "Bill Payment"
        const val REMINDER = "Reminder"
        const val GROCERY = "Grocery"
        const val MEETING = "Meeting"
        const val MEDICINE = "Medicine"

        fun getCategoryName(category: Category): String = when (category) {
            is BillPayment -> BILLPAYMENT

            is Reminder -> REMINDER

            is Grocery -> GROCERY

            is Meeting -> MEETING

            is Medicine -> MEDICINE
            else -> {
                GENERAL
            }
        }

    }

//    data class Test(val id:Int) : Category("")
}

/*
BILLPAYMENT
REMINDER
GROCERY
MEETING
MEDICINE

 */