package com.garbathegreat.spendwise

import java.time.LocalDate
import java.time.YearMonth
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class SummaryTest {
    private fun expense(amount: Double, category: Category, date: String) =
        Expense(id = amount.toLong(), title = "x", amount = amount, category = category, date = LocalDate.parse(date))

    private val sample = listOf(
        expense(10.0, Category.FOOD, "2026-10-01"),
        expense(30.0, Category.BILLS, "2026-10-05"),
        expense(5.0, Category.FOOD, "2026-09-28"),
    )

    @Test
    fun totalAddsEveryExpense() {
        assertEquals(45.0, Summary.total(sample), 0.0001)
    }

    @Test
    fun forMonthKeepsOnlyThatMonth() {
        assertEquals(2, Summary.forMonth(sample, YearMonth.of(2026, 10)).size)
    }

    @Test
    fun byCategoryIsSortedLargestFirst() {
        val result = Summary.byCategory(sample)
        assertEquals(listOf(Category.BILLS, Category.FOOD), result.keys.toList())
        assertEquals(15.0, result.getValue(Category.FOOD), 0.0001)
    }

    @Test
    fun parseAmountRejectsBadInput() {
        assertEquals(12.5, Summary.parseAmount(" 12.5 ")!!, 0.0001)
        assertNull(Summary.parseAmount("abc"))
        assertNull(Summary.parseAmount("0"))
        assertNull(Summary.parseAmount("-3"))
        assertNull(Summary.parseAmount("NaN"))
    }
}
