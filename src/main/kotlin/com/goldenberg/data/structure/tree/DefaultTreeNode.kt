package com.goldenberg.data.structure.tree

class DefaultTreeNode<T>(parent: DefaultTreeNode<T>, left: DefaultTreeNode<T>, right: DefaultTreeNode<T>, value: T?) : AbstractTreeNode<T, DefaultTreeNode<T>>(parent, left, right, value)
