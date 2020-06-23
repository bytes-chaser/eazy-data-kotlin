package com.goldenberg.data.structure.tree.heap

import com.goldenberg.data.structure.tree.ComparableTreeNode


interface HeapNode<T : Comparable<T>, N : HeapNode<T, N>> : ComparableTreeNode<T, N> {

    fun insert(value: T)
}
