package com.goldenberg.data.structure.tree



interface TreeNode<T> {

    fun isTopNode(): Boolean

    fun isEmpty(): Boolean

    fun isNotEmpty(): Boolean

    fun isLeaf(): Boolean

    fun isNotLeaf(): Boolean

}
