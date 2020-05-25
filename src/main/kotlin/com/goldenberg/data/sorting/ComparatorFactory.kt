package com.goldenberg.data.sorting

import com.goldenberg.data.enums.Order


fun <T: Comparable<T>> createAscComparingPredicate(): (T, T) -> Boolean {
    return {value1: T, value2: T -> value1 > value2 }
}

fun <T: Comparable<T>> createDescComparingPredicate(): (T, T) -> Boolean {
    return {value1: T, value2: T -> value1 < value2 }
}

fun <T: Comparable<T>> getSortingAlgorithmPredicate(order: Order): (T, T) -> Boolean = when(order) {
    Order.ASC -> createAscComparingPredicate()
    Order.DESC -> createDescComparingPredicate()
}