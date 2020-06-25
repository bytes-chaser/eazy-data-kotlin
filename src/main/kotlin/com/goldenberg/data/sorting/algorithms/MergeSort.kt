package com.goldenberg.data.sorting.algorithms

import com.goldenberg.data.enums.Order
import com.goldenberg.data.sorting.getSortingAlgorithmPredicate
import kotlin.math.abs
import kotlin.math.min

class MergeSort : Sorting {

    override fun <T : Comparable<T>> sort(values: MutableList<T>, order: Order): MutableList<T> {
        val predicate = getSortingAlgorithmPredicate<T>(order)
        if (values.size == 2 && predicate.invoke(values[0], values[1]))
            swap(values, 0, 1)
        else if (values.size > 2) {
            val splitIndex = values.size / 2
            return merge(sort(values.subList(splitIndex, values.size), order), sort(values.subList(0, splitIndex), order), predicate)
        }
        return values
    }

    private fun <T : Comparable<T>> merge(values1: MutableList<T>, values2: MutableList<T>, predicate: (T, T) -> Boolean): MutableList<T> {
        val mergedList = mutableListOf<T>()
        val diff = abs(values1.size - values2.size)
        val min = min(values1.size, values2.size)
        var i = 0
        while (i < min) {
            var value1 = values1[i]
            var value2 = values2[i]
            if (predicate.invoke(value1, value2)) {
                value1 = values2[i]
                value2 = values1[i]
            }
            mergedList.add(2 * i, value1)
            mergedList.add(2 * i + 1, value2)
            i++
        }
        val values3 = if (diff > 0) values1 else values2
        mergedList.addAll(values3.subList(values3.size - diff, values3.size))
        return mergedList
    }
}
