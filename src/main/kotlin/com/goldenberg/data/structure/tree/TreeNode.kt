package com.goldenberg.data.structure.tree

interface TreeNode<T, N : TreeNode<T, N>> {

    var parent: N?
    var left: N?
    var right: N?
    var value: T

    fun isTopNode(): Boolean = parent == null

    fun isEmpty(): Boolean = value == null

    fun isNotEmpty(): Boolean = !isEmpty()

    fun isLeaf(): Boolean = left == null && right == null

    fun isNotLeaf(): Boolean = !isLeaf()


}
