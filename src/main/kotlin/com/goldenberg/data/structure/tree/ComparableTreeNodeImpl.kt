package com.goldenberg.data.structure.tree

class ComparableTreeNodeImpl<C : Comparable<C>, T>(private val key: C, value: T?)
    : AbstractTreeNode<T, ComparableTreeNodeImpl<C, T>>(value), ComparableTreeNode<C, T> {
    override fun compareTo(value: C): Int {
        return this.key.compareTo(value)
    }

    constructor(parent: ComparableTreeNodeImpl<C, T>, key: C, value: T?) : this(key, value) {
        this.parent = parent
    }

    constructor(parent: ComparableTreeNodeImpl<C, T>, left: ComparableTreeNodeImpl<C, T>, right: ComparableTreeNodeImpl<C, T>, key: C, value: T?) : this(parent, key, value) {
        this.left = left
        this.right = right
    }
}
