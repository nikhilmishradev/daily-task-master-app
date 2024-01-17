package com.androidtechguru.task.todos.model.room

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.androidtechguru.task.todos.model.data.BillPayment
import com.androidtechguru.task.todos.model.data.Category
import com.androidtechguru.task.todos.model.data.Category.Companion.BILLPAYMENT
import com.androidtechguru.task.todos.model.data.Category.Companion.GROCERY
import com.androidtechguru.task.todos.model.data.Category.Companion.MEDICINE
import com.androidtechguru.task.todos.model.data.Category.Companion.MEETING
import com.androidtechguru.task.todos.model.data.Category.Companion.REMINDER
import com.androidtechguru.task.todos.model.data.Reminder
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class CategoryConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromCategory(category: Category): String? = gson.toJson(category)

    @TypeConverter
    fun toCategory(json: String?): Category? = json?.let {
//        val objectType = object : TypeToken<Category>() {}.type
//        gson.fromJson(it, objectType)

        val gson = GsonBuilder()
            .registerTypeAdapter(Category::class.java, CategoryDeserializer())
            .create()
        val jsonString = "{ \"tag\": \"$BILLPAYMENT\", \"tag\": \"$REMINDER\", " +
                "\"tag\": \"$GROCERY\", \"tag\": \"$MEETING\", \"tag\": \"$MEDICINE\" }"
        val category = gson.fromJson(jsonString, Category::class.java)
        category
    }
}
