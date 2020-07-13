package com.goldenberg.data.sorting

import com.goldenberg.data.enums.Order
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.random.Random
import kotlin.test.assertTrue

abstract class AbstractSortingTest {

    private lateinit var sorting: Sorting
    private val numbersOfInputs = 5000

    @BeforeEach
    fun setSortingAlgorithm() {
        this.sorting = initAlgorithm()
    }

    internal abstract fun initAlgorithm(): Sorting

    @Test
    fun `Test Sorting No Order`() {
        checkSorting()
    }

    @Test
    fun `Test Sorting ASC`() {
        checkSorting(order = Order.ASC)
    }

    @Test
    fun `Test Sorting DESC`() {
        checkSorting(order = Order.DESC)
    }

    private fun checkSorting(order: Order) {
        analyzeSorting(runSorting(order), getSortingAlgorithmPredicate(order))
    }

    private fun checkSorting() {
        analyzeSorting(runSorting(), getSortingAlgorithmPredicate(Order.ASC))
    }

    private fun runSorting(order: Order): List<Int> {
        val startTime = System.currentTimeMillis()
        val list = this.sorting.sort(createRandomIntList(), order)
        val endTime = System.currentTimeMillis()
        displayExecutionTime(endTime - startTime, order)
        return list
    }

    private fun runSorting(): List<Int> {
        val startTime = System.currentTimeMillis()
        val list = this.sorting.sort(createRandomIntList())
        val endTime = System.currentTimeMillis()
        displayExecutionTime(endTime - startTime, Order.ASC)
        return list
    }

    private fun createRandomIntList(): MutableList<Int> {
        val list = mutableListOf<Int>()
        for (i in 0..numbersOfInputs) list.add(Random.nextInt(0, numbersOfInputs))
        return list
    }

    private fun displayExecutionTime(milliseconds: Long, order: Order) {
        val algorithmName = this.sorting.javaClass.simpleName
        val orderName = order.name
        println(
            String.format(
                "%s with %s order takes %d milliseconds for %d inputs",
                algorithmName,
                orderName,
                milliseconds,
                this.numbersOfInputs
            )
        )
    }


    private fun analyzeSorting(list: List<Int>, predicate: (Int, Int) -> Boolean) {
        for (i in 1 until list.size) {
            val i0 = list[i - 1]
            val i1 = list[i]
            assertTrue { i0 == i1 || predicate.invoke(i1, i0) }
        }
    }
}