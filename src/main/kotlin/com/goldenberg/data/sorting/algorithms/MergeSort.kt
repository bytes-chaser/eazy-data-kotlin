package com.goldenberg.data.sorting.algorithms

import com.goldenberg.data.enums.Order
import com.goldenberg.data.sorting.getSortingAlgorithmPredicateWithEquality

class MergeSort : Sorting {

    override fun <T : Comparable<T>> sort(values: MutableList<T>, order: Order): MutableList<T> {
        val predicate = getSortingAlgorithmPredicateWithEquality<T>(order)

        if (values.size == 2 && predicate.invoke(values[0], values[1]))
            swap(values, 0, 1)
        else if (values.size > 2) {
            val splitIndex = values.size / 2
            val list1 = sort(values.subList(splitIndex, values.size), order)
            val list2 = sort(values.subList(0, splitIndex), order)
            
            return merge(list1, list2, predicate)
        }
        return values
    }


    private fun <T : Comparable<T>> merge(values1: MutableList<T>, values2: MutableList<T>, predicate: (T, T) -> Boolean): MutableList<T> {
        val mergedList = mutableListOf<T>()

        var i0 = 0
        var i1 = 0

        while (i0 < values1.size && i1 < values2.size) {
            if (predicate.invoke(values2[i1], values1[i0])) {
                mergedList.add(values1[i0])
                i0++
            } else {
                mergedList.add(values2[i1])
                i1++
            }
        }

        while (i0 < values1.size) {
            mergedList.add(values1[i0])
            i0++
        }

        while (i1 < values2.size) {
            mergedList.add(values2[i1])
            i1++
        }

        return mergedList
    }
}
