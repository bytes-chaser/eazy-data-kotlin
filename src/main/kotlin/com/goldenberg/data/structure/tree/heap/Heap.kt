package com.goldenberg.data.structure.tree.heap

import com.goldenberg.data.structure.tree.Tree

interface Heap<T : Comparable<T>> : Tree<T> {

    fun add(element: T): Boolean
    fun addAll(elements: Collection<T>): Boolean

    fun poll(): T

    fun isEmpty(): Boolean
    fun isNotEmpty(): Boolean = !isEmpty()

    fun clear()
}
