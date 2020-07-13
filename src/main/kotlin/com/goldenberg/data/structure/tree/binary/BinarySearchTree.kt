package com.goldenberg.data.structure.tree.binary

import com.goldenberg.data.structure.tree.ComparableTreeNodeImpl
import com.goldenberg.data.structure.tree.SearchTree

class BinarySearchTree<C : Comparable<C>, T> : SearchTree<C, T> {

    var top: ComparableTreeNodeImpl<C, T>? = null

    override fun find(key: C): T? {
        return find(key, top)
    }


    private fun find(key: C, node: ComparableTreeNodeImpl<C, T>? = top): T? {
        return findNode(key, node)?.value
    }


    private fun findNode(key: C, node: ComparableTreeNodeImpl<C, T>? = top): ComparableTreeNodeImpl<C, T>? {
        if (node == null) return null

        var currentNode = node
        while (currentNode != null) {

            val compareValue = currentNode.compareTo(key)

            currentNode = when {
                compareValue > 0 -> currentNode.left
                compareValue < 0 -> currentNode.right
                else -> return currentNode
            }
        }
        return null
    }


    private fun insert(key: C, value: T, node: ComparableTreeNodeImpl<C, T>? = top) {
        var parent: ComparableTreeNodeImpl<C, T>? = null
        var current: ComparableTreeNodeImpl<C, T>? = node

        if (current == null) {
            top = ComparableTreeNodeImpl(key, value)
            return
        }

        var isLeft = false

        while (current != null) {
            val compareValue = current.compareTo(key)
            if (compareValue == 0) {
                current.value = value
                return
            }

            parent = current

            if (compareValue > 0) {
                isLeft = true
                current = current.left
            } else {
                isLeft = false
                current = current.right
            }
        }

        val newNode = ComparableTreeNodeImpl(parent!!, key, value)

        if (isLeft)
            parent.left = newNode
        else
            parent.right = newNode
    }


    private fun removeNode(key: C): ComparableTreeNodeImpl<C, T>? {

        val current = findNode(key, top) ?: return null
        val parent = current.parent
        val hasParent = current.parent != null
        val replaceNode: ComparableTreeNodeImpl<C, T>?
        var isLeft = false

        if (hasParent) isLeft = current.parent!!.compareTo(key) > 0

        when {
            current.right != null -> {
                replaceNode = minNode(current.right!!)
                replaceNode.left = current.left
                if (replaceNode != current.right) replaceNode.right = current.right
            }
            current.left != null -> {
                val leftRight = current.left!!.right
                replaceNode = if (leftRight == null) current.left else minNode(leftRight)
                replaceNode!!.right = null
            }
            else -> replaceNode = null
        }

        if (hasParent) {
            replaceNode?.parent = parent
            if (isLeft)
                parent!!.left = replaceNode
            else
                parent!!.right = replaceNode
        } else top = replaceNode

        return current
    }


    private fun minNode(node: ComparableTreeNodeImpl<C, T>): ComparableTreeNodeImpl<C, T> {
        var min = node
        while (min.left != null) min = min.left!!
        if (min.right !== null && node != min)
            min.parent!!.left = min.right
        else
            min.parent = null
        return min
    }


    override fun insert(key: C, value: T) {
        insert(key, value, top)
    }


    override fun remove(key: C) {
        removeNode(key)
    }

}