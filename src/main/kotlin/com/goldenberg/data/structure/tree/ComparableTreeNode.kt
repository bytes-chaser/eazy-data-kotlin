package com.goldenberg.data.structure.tree

interface ComparableTreeNode<C : Comparable<C>, T> : TreeNode<T> {
    fun compareTo(value: C): Int
    fun isKeyNode(key: C): Boolean

}
