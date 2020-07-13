package com.goldenberg.data.structure.tree

interface Tree<C : Comparable<C>, T> {
    fun insert(key: C, value: T)
    fun remove(key: C)
}
