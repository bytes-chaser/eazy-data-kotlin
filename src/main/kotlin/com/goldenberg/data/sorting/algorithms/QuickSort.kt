package com.goldenberg.data.sorting.algorithms

import com.goldenberg.data.enums.Order
import com.goldenberg.data.sorting.getSortingAlgorithmPredicate

class QuickSort: Sorting {

    override fun <T : Comparable<T>> sort(values: MutableList<T>, order: Order): MutableList<T> {
        if (values.isEmpty()) return values
        val predicate = getSortingAlgorithmPredicate<T>(order)

        return sort(values, predicate, 0, values.size - 1)
    }

    private fun <T : Comparable<T>> sort(values: MutableList<T>, predicate: (T, T) -> Boolean, start: Int, end: Int): MutableList<T> {
        if (start >= end) return values

        val baseIndex = (start + end) / 2
        val base = values[baseIndex]
        var leftPointer = start
        var rightPointer = end

        while (rightPointer >= leftPointer) {
            while (predicate(base, values[leftPointer])) leftPointer++
            while (predicate(values[rightPointer], base)) rightPointer--

            if (rightPointer >= leftPointer) {
                swap(values, leftPointer, rightPointer)
                leftPointer++
                rightPointer--
            }
        }

        sort(values, predicate, start, leftPointer - 1)
        sort(values, predicate, leftPointer, end)

        return values
    }
}
