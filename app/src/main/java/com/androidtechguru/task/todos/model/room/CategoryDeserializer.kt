package com.androidtechguru.task.todos.model.room

import com.androidtechguru.task.todos.model.data.BillPayment
import com.androidtechguru.task.todos.model.data.Category
import com.androidtechguru.task.todos.model.data.Category.Companion.BILLPAYMENT
import com.androidtechguru.task.todos.model.data.Category.Companion.GROCERY
import com.androidtechguru.task.todos.model.data.Category.Companion.MEDICINE
import com.androidtechguru.task.todos.model.data.Category.Companion.MEETING
import com.androidtechguru.task.todos.model.data.Category.Companion.REMINDER
import com.androidtechguru.task.todos.model.data.CategoryType
import com.androidtechguru.task.todos.model.data.Grocery
import com.androidtechguru.task.todos.model.data.Medicine
import com.androidtechguru.task.todos.model.data.Meeting
import com.androidtechguru.task.todos.model.data.Reminder
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type

class CategoryDeserializer : JsonDeserializer<Category> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Category = json?.let {
        val jsonObject = it.asJsonObject
        val tag = jsonObject.get("tag").asString

        when (tag) {
            BILLPAYMENT -> Gson().fromJson(jsonObject, BillPayment::class.java)
            REMINDER -> Gson().fromJson(jsonObject, Reminder::class.java)
            GROCERY -> Gson().fromJson(jsonObject, Grocery::class.java)
            MEETING -> Gson().fromJson(jsonObject, Meeting::class.java)
            MEDICINE -> Gson().fromJson(jsonObject, Medicine::class.java)

            else -> throw JsonParseException("Unknown tag: $tag")
        }
    } ?: throw JsonParseException("")
}