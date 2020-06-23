package com.goldenberg.data.sorting

import com.goldenberg.data.enums.Order
import com.goldenberg.data.structure.tree.heap.HeapType


fun <T: Comparable<T>> createAscComparingPredicate(): (T, T) -> Boolean {
    return {value1: T, value2: T -> value1 > value2 }
}

fun <T : Comparable<T>> createDescComparingPredicate(): (T, T) -> Boolean {
    return { value1: T, value2: T -> value1 < value2 }
}

fun <T : Comparable<T>> getSortingAlgorithmPredicate(order: Order): (T, T) -> Boolean = when (order) {
    Order.ASC -> createAscComparingPredicate()
    Order.DESC -> createDescComparingPredicate()
}

fun <T : Comparable<T>> getHeapPredicate(heapType: HeapType): (T, T) -> Boolean = when (heapType) {
    HeapType.MAX -> createAscComparingPredicate()
    HeapType.MIN -> createDescComparingPredicate()
}
