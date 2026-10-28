package com.garbathegreat.spendwise

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import java.time.LocalDate

class ExpenseViewModel(app: Application) : AndroidViewModel(app) {
    private val store = ExpenseStore(app)

    var expenses by mutableStateOf(store.load())
        private set

    fun add(title: String, amount: Double, category: Category) {
        val expense = Expense(
            id = System.currentTimeMillis(),
            title = title.trim(),
            amount = amount,
            category = category,
            date = LocalDate.now(),
        )
        expenses = listOf(expense) + expenses
        store.save(expenses)
    }

    fun remove(id: Long) {
        expenses = expenses.filterNot { it.id == id }
        store.save(expenses)
    }
}
