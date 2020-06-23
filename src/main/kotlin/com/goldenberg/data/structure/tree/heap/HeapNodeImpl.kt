package com.goldenberg.data.structure.tree.heap

import com.goldenberg.data.structure.tree.AbstractTreeNode

class HeapNodeImpl<T : Comparable<T>>(private var comparator: (T, T) -> Boolean, value: T, parent: HeapNodeImpl<T>? = null, left: HeapNodeImpl<T>? = null, right: HeapNodeImpl<T>? = null)
    : AbstractTreeNode<T, HeapNodeImpl<T>>(value, parent, left, right),
        HeapNode<T, HeapNodeImpl<T>> {


    override fun insert(value: T) {
        var insertNode: HeapNodeImpl<T>? = this.right

        if (insertNode == null) {
            insertNode = HeapNodeImpl(this.comparator, value, this)
            insertNode.findPosition()
        } else insertNode.insert(value)
    }

    private fun findPosition() {
        var isNeedsParentCheck = true
        var parent = this.parent
        while (isNeedsParentCheck) {
            isNeedsParentCheck = check(parent)
            parent = parent?.parent
        }

        var isNeedsLeftCheck = true
        var left = parent?.left
        while (isNeedsLeftCheck) {
            isNeedsLeftCheck = check(left)
            left = left?.left
        }

    }


    private fun check(node: HeapNode<T, HeapNodeImpl<T>>?): Boolean {
        var r = false
        if (node != null && this.comparator.invoke(value, node.value)) {
            swap(this, node as HeapNodeImpl<T>)
            r = true
        }
        return r
    }

    fun swap(node0: HeapNodeImpl<T>, node1: HeapNodeImpl<T>) {
        val tmp = node0.value
        node0.value = node1.value
        node1.value = tmp
    }

}
