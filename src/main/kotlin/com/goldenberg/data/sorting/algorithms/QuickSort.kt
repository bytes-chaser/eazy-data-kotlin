package com.goldenberg.data.sorting.algorithms

import com.goldenberg.data.enums.Order
import com.goldenberg.data.sorting.getSortingAlgorithmPredicate

class QuickSort: Sorting {

    override fun <T : Comparable<T>> sort(values: MutableList<T>, order: Order): MutableList<T> {
        if (values.isEmpty()) return values
        return sort(values, order, 0, values.size -1)
    }

    fun <T : Comparable<T>> sort(values: MutableList<T>, order: Order, start: Int, end: Int): MutableList<T> {
        if (start >= end) return values

        val predicate = getSortingAlgorithmPredicate<T>(order)
        val baseIndex = (start + end) / 2
        val base = values[baseIndex]
        var leftPointer = start
        var rightPointer = end

        while (rightPointer >= leftPointer) {
            while (predicate(base, values[leftPointer])) {
                leftPointer++
            }

            while (predicate(values[rightPointer], base)) {
                rightPointer--
            }

            if (rightPointer >= leftPointer) {
                val tmp = values[leftPointer]
                values[leftPointer] = values[rightPointer]
                values[rightPointer] = tmp
                leftPointer++
                rightPointer--
            }
        }

        sort(values, order, start, leftPointer -1)
        sort(values, order, leftPointer, end)

        return values
    }
}