package com.goldenberg.data.structure.tree

abstract class AbstractTreeNode<T, N : TreeNode<T>>(var value: T?) : TreeNode<T> {

    var parent: N? = null
    var left: N? = null
    var right: N? = null

    constructor(parent: N, value: T?) : this(value) {
        this.parent = parent
    }

    constructor(parent: N, left: N, right: N, value: T?) : this(parent, value) {
        this.left = left
        this.right = right
    }


    override fun isTopNode(): Boolean = parent == null

    override fun isEmpty(): Boolean = value == null

    override fun isNotEmpty(): Boolean = !isEmpty()

    override fun isLeaf(): Boolean = left == null && right == null

    override fun isNotLeaf(): Boolean = !isLeaf()

    fun setParentNode(node: N) {
        this.parent = node
    }

    fun setLeftNode(node: N) {
        this.left = node
    }

    fun setRightNode(node: N) {
        this.right = node
    }
}
