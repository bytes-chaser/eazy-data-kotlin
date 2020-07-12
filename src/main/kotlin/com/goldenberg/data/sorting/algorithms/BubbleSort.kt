package com.goldenberg.data.sorting.algorithms

import com.goldenberg.data.enums.Order
import com.goldenberg.data.sorting.getSortingAlgorithmPredicate

class BubbleSort: Sorting {

    override fun <T : Comparable<T>> sort(values: MutableList<T>, order: Order): MutableList<T> {
        val predicate = getSortingAlgorithmPredicate<T>(order)
        var isSorted = false
        while (!isSorted) {
            isSorted = true
            for (i1 in 0 until values.size - 1) {
                val i2 = i1 + 1
                if (predicate.invoke(values[i1], values[i2])) {
                    swap(values, i1, i2)
                    isSorted = false
                }
            }
        }
        return values
    }
}
