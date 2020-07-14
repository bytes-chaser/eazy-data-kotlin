package com.goldenberg.data.structure.tree.binary

import com.goldenberg.data.structure.tree.ComparableTreeNodeImpl
import com.goldenberg.data.structure.tree.SearchTree

class BinarySearchTree<C : Comparable<C>, T> : SearchTree<C, T> {

    var top: ComparableTreeNodeImpl<C, T>? = null

    override fun find(key: C): T? {
        return findNode(key, top)?.value
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


    override fun insert(key: C, value: T) {
        var parent: ComparableTreeNodeImpl<C, T>? = null
        var current: ComparableTreeNodeImpl<C, T>? = top

        if (current == null) {
            top = ComparableTreeNodeImpl(key, value)
            return
        }

        var isLeft = false

        while (current != null) {
            val compareValue = current.compareTo(key)

            parent = current
            when {
                compareValue > 0 -> {
                    isLeft = true
                    current = current.left
                }
                compareValue < 0 -> {
                    isLeft = false
                    current = current.right
                }
                else -> {
                    current.value = value
                    return
                }
            }
        }

        val newNode = ComparableTreeNodeImpl(parent!!, key, value)
        parent.setChildNode(newNode, isLeft)
    }


    override fun remove(key: C): T? {
        return removeNode(key)?.value
    }


    private fun removeNode(key: C): ComparableTreeNodeImpl<C, T>? {

        val current = findNode(key, top) ?: return null

        val replaceNode = when {
            current.right != null -> getRightReplacement(current)
            current.left != null -> getLeftReplacement(current)
            else -> null
        }

        changeParentNode(current, replaceNode)

        return current
    }


    private fun getRightReplacement(current: ComparableTreeNodeImpl<C, T>): ComparableTreeNodeImpl<C, T> {
        val replaceNode = minNode(current.right!!)
        replaceNode.left = current.left
        if (replaceNode != current.right) replaceNode.right = current.right
        return replaceNode
    }


    private fun getLeftReplacement(current: ComparableTreeNodeImpl<C, T>): ComparableTreeNodeImpl<C, T> {
        val leftRight = current.left!!.right
        val replaceNode = if (leftRight == null) current.left else minNode(leftRight)
        replaceNode!!.right = null
        return replaceNode
    }


    private fun changeParentNode(current: ComparableTreeNodeImpl<C, T>, replaceNode: ComparableTreeNodeImpl<C, T>?) {
        val parent = current.parent
        val isLeft: Boolean

        if (current.parent != null) {
            isLeft = current.parent!!.compareTo(current.key) > 0
            replaceNode?.parent = parent
            parent!!.setChildNode(replaceNode, isLeft)
        } else
            top = replaceNode
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





}