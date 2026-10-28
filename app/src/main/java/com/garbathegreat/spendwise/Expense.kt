package com.garbathegreat.spendwise

import java.time.LocalDate

enum class Category(val label: String) {
    FOOD("Food"),
    TRANSPORT("Transport"),
    BILLS("Bills"),
    SHOPPING("Shopping"),
    HEALTH("Health"),
    OTHER("Other"),
}

data class Expense(
    val id: Long,
    val title: String,
    val amount: Double,
    val category: Category,
    val date: LocalDate,
)
