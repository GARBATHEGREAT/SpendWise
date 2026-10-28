package com.garbathegreat.spendwise

import java.time.YearMonth

/** Pure functions over the expense list, kept free of Android types so they can be unit tested. */
object Summary {
    fun total(expenses: List<Expense>): Double = expenses.sumOf { it.amount }

    fun forMonth(expenses: List<Expense>, month: YearMonth): List<Expense> =
        expenses.filter { YearMonth.from(it.date) == month }

    /** Category totals, largest first. */
    fun byCategory(expenses: List<Expense>): Map<Category, Double> =
        expenses.groupBy { it.category }
            .map { (category, items) -> category to items.sumOf { it.amount } }
            .sortedByDescending { it.second }
            .toMap()

    /** Returns the amount only when it is a finite number greater than zero. */
    fun parseAmount(text: String): Double? =
        text.trim().toDoubleOrNull()?.takeIf { it > 0 && it.isFinite() }
}
