package com.goldenberg.data.structure.tree.heap

import com.goldenberg.data.sorting.getHeapPredicate


class AbstractHeap<T : Comparable<T>>(heapType: HeapType = HeapType.MIN) : Heap<T> {

    private lateinit var comparator: (T, T) -> Boolean
    private lateinit var heapType: HeapType

    val array: MutableList<T>

    init {
        setHeapType(heapType)
        this.array = ArrayList()
    }


    fun setHeapType(heapType: HeapType) {
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
        val left = getLeft(index)
        while (left != null) {
            var childIndex = getLeftIndex(index)
            val right = getRight(index)
            if (right != null && comparator.invoke(right, left)) childIndex = getRightIndex(index)

            if (comparator.invoke(array[index], array[childIndex]))
                break
            else
                swap(index, childIndex)

            index = childIndex
        }
    }

    private fun layerUpIndex(index: Int): Int = 2 * index

    private fun getLeftIndex(index: Int): Int = layerUpIndex(index) + 1

    private fun getRightIndex(index: Int): Int = layerUpIndex(index) + 2

    private fun getParentIndex(index: Int): Int = (index - 1) / 2

    private fun hasParentElement(index: Int): Boolean = getParentIndex(index) >= 0

    private fun getLeft(index: Int): T? = array[getLeftIndex(index)]

    private fun getRight(index: Int): T? = array[getRightIndex(index)]

    private fun getParent(index: Int): T? = array[getParentIndex(index)]

    private fun swap(index0: Int, index1: Int) {
        val temp = array[index0]
        array[index0] = array[index1]
        array[index1] = temp
    }

    override fun poll(): T {
        require(isNotEmpty()) { "Unable to poll element from empty heap" }
        val r = array[0]
        array[0] = array[array.size - 1]
        fixHeapDown()
        return r
    }

    override fun isEmpty(): Boolean = array.isEmpty()

    override fun clear() = array.clear()
}
