package com.garbathegreat.spendwise

import android.content.Context
import java.time.LocalDate
import org.json.JSONArray
import org.json.JSONObject

/** Saves expenses on the device as JSON in SharedPreferences. Nothing leaves the phone. */
class ExpenseStore(context: Context) {
    private val prefs = context.getSharedPreferences("spendwise", Context.MODE_PRIVATE)

    fun load(): List<Expense> {
        val raw = prefs.getString(KEY, null) ?: return emptyList()
        return try {
            val array = JSONArray(raw)
            (0 until array.length()).map { i ->
                val o = array.getJSONObject(i)
                Expense(
                    id = o.getLong("id"),
                    title = o.getString("title"),
                    amount = o.getDouble("amount"),
                    category = Category.valueOf(o.getString("category")),
                    date = LocalDate.parse(o.getString("date")),
                )
            }
        } catch (e: Exception) {
            // Corrupt or outdated data should never crash the app.
            emptyList()
        }
    }

    fun save(expenses: List<Expense>) {
        val array = JSONArray()
        expenses.forEach {
            array.put(
                JSONObject()
                    .put("id", it.id)
                    .put("title", it.title)
                    .put("amount", it.amount)
                    .put("category", it.category.name)
                    .put("date", it.date.toString())
            )
        }
        prefs.edit().putString(KEY, array.toString()).apply()
    }

    private companion object {
        const val KEY = "expenses"
    }
}
