package com.goldenberg.data.structure.tree.heap

import com.goldenberg.data.sorting.getHeapPredicate
import kotlin.math.floor


abstract class AbstractHeap<T : Comparable<T>>(heapType: HeapType = HeapType.MIN) : Heap<T> {

    private lateinit var comparator: (T, T) -> Boolean
    private lateinit var heapType: HeapType

    private val array: MutableList<T>

    init {
        setHeapType(heapType)
        this.array = ArrayList()
    }


    private fun setHeapType(heapType: HeapType) {
        this.heapType = heapType
        this.comparator = getHeapPredicate(heapType)
    }


    override fun add(element: T): Boolean {
        val r = array.add(element)
        fixHeapUp()
        return r
    }

    override fun addAll(elements: Collection<T>): Boolean {
        var r = false
        for (element in elements) {
            val isAdded = add(element)
            if (!r) r = isAdded
        }
        return r
    }

    private fun fixHeapUp() {
        var lastIndex = array.size - 1
        while (hasParentElement(lastIndex) && comparator.invoke(this.getParent(lastIndex)!!, array[lastIndex])) {
            val parentIndex = getParentIndex(lastIndex)
            swap(lastIndex, parentIndex)
            lastIndex = parentIndex
        }
    }

    private fun fixHeapDown() {
        var index = 0
        while (hasLeft(index)) {
            var childIndex = getLeftIndex(index)
            if (hasRight(index) && comparator.invoke(getLeft(index), getRight(index))) childIndex = getRightIndex(index)

            if (comparator.invoke(array[childIndex], array[index])) break
            else swap(index, childIndex)

            index = childIndex
        }
    }

    private fun layerUpIndex(index: Int): Int = 2 * index

    private fun getLeftIndex(index: Int): Int = layerUpIndex(index) + 1

    private fun getRightIndex(index: Int): Int = layerUpIndex(index) + 2

    private fun getParentIndex(index: Int): Int {
        val i1: Float = (index - 1) / 2F
        val i = floor(i1.toDouble())
        return i.toInt()
    }

    private fun hasParentElement(index: Int): Boolean = getParentIndex(index) >= 0

    private fun getLeft(index: Int): T = array[getLeftIndex(index)]
    private fun hasLeft(index: Int): Boolean = getLeftIndex(index) < array.size
    private fun getRight(index: Int): T = array[getRightIndex(index)]
    private fun hasRight(index: Int): Boolean = getRightIndex(index) < array.size


    private fun getParent(index: Int): T? = array[getParentIndex(index)]

    private fun swap(index0: Int, index1: Int) {
        val temp = array[index0]
        array[index0] = array[index1]
        array[index1] = temp
    }

    override fun poll(): T {
        require(isNotEmpty()) { "Unable to poll element from empty heap" }
        val r = array[0]
        if (hasRight(0) && hasLeft(0)) {
            val lastIndex = array.size - 1
            array[0] = array[lastIndex]
            array.removeAt(lastIndex)
            fixHeapDown()
        } else array.removeAt(0)

        return r
    }

    override fun isEmpty(): Boolean = array.isEmpty()

    override fun clear() = array.clear()
}
