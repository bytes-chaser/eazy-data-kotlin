package com.goldenberg.data.sorting

interface Sortable<T>: Comparable<T> {

    fun getSortableValue(): Comparable<*>
}