package com.goldenberg.data.structure.tree

class ComparableTreeNodeImpl<T : Comparable<T>, N : ComparableTreeNodeImpl<T, N>>(override var parent: N?, override var left: N?, override var right: N?, override var value: T)
    : ComparableTreeNode<T, N>
