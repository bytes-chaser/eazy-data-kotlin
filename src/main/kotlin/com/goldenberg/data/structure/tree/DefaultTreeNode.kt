package com.goldenberg.data.structure.tree

class DefaultTreeNode<T, N : DefaultTreeNode<T, N>>(value: T, parent: N? = null, left: N? = null, right: N? = null)
    : AbstractTreeNode<T, N>(value, parent, left, right)
