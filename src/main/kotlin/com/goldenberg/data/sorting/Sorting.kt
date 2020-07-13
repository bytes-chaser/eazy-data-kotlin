package com.goldenberg.data.sorting

import com.goldenberg.data.enums.Order

interface Sorting {

    fun <T: Comparable<T>> sort(values: MutableList<T>, order: Order = Order.ASC): MutableList<T>
}