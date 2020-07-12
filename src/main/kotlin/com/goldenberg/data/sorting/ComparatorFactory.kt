package com.goldenberg.data.sorting

import com.goldenberg.data.enums.Order
import com.goldenberg.data.structure.tree.heap.HeapType


fun <T : Comparable<T>> createBiggerThenPredicate(): (T, T) -> Boolean {
    return { value1: T, value2: T -> value1 > value2 }
}

fun <T : Comparable<T>> createSmallerThenPredicate(): (T, T) -> Boolean {
    return { value1: T, value2: T -> value1 < value2 }
}

fun <T : Comparable<T>> createBiggerThenOrEqualPredicate(): (T, T) -> Boolean {
    return { value1: T, value2: T -> value1 >= value2 }
}

fun <T : Comparable<T>> createSmallerThenOrEqualPredicate(): (T, T) -> Boolean {
    return { value1: T, value2: T -> value1 <= value2 }
}

fun <T : Comparable<T>> getSortingAlgorithmPredicate(order: Order): (T, T) -> Boolean = when (order) {
    Order.ASC -> createBiggerThenPredicate()
    Order.DESC -> createSmallerThenPredicate()
}

fun <T : Comparable<T>> getSortingAlgorithmPredicateWithEquality(order: Order): (T, T) -> Boolean = when (order) {
    Order.ASC -> createBiggerThenOrEqualPredicate()
    Order.DESC -> createSmallerThenOrEqualPredicate()
}

fun <T : Comparable<T>> getHeapPredicate(heapType: HeapType): (T, T) -> Boolean = when (heapType) {
    HeapType.MIN -> createBiggerThenOrEqualPredicate()
    HeapType.MAX -> createSmallerThenOrEqualPredicate()
}
