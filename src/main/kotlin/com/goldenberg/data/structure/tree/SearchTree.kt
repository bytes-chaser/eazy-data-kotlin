package com.goldenberg.data.structure.tree

interface SearchTree<C : Comparable<C>, T> : Tree<C, T> {
    fun find(key: C): T?
}
