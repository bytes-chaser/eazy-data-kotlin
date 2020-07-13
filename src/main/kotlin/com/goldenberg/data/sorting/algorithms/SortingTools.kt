package com.goldenberg.data.sorting.algorithms

internal fun <T : Comparable<T>> swap(values: MutableList<T>, index1: Int, index2: Int) {
    val tmp = values[index1]
    values[index1] = values[index2]
    values[index2] = tmp
}
