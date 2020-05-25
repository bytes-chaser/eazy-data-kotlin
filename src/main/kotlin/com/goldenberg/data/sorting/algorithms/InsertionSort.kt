package com.goldenberg.data.sorting.algorithms

import com.goldenberg.data.enums.Order
import com.goldenberg.data.sorting.getSortingAlgorithmPredicate

class InsertionSort: Sorting {

    override fun <T : Comparable<T>> sort(values: MutableList<T>, order: Order): MutableList<T> {
        val predicate = getSortingAlgorithmPredicate<T>(order)
        var index1 = 1
        for (i in 1 until values.size) {
            val value = values[index1]
            var index2 = index1 -1
            while (index2 >= 0 && predicate.invoke(values[index2], value)) {
                values[index2 + 1] = values[index2]
                index2--
            }
            values[index2 + 1] = value
            index1++
        }
        return values
    }
}