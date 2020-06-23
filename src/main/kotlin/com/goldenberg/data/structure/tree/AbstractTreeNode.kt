package com.goldenberg.data.structure.tree

abstract class AbstractTreeNode<T, N : AbstractTreeNode<T, N>>(override var value: T,
                                                               override var parent: N? = null,
                                                               override var left: N? = null,
                                                               override var right: N? = null)
    : TreeNode<T, N>
