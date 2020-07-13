package com.goldenberg.data.sorting.algorithms

import com.goldenberg.data.enums.Order
import com.goldenberg.data.sorting.Sorting
import com.goldenberg.data.structure.tree.heap.HeapImpl
import com.goldenberg.data.structure.tree.heap.HeapType

internal class HeapSort : Sorting {

    override fun <T : Comparable<T>> sort(values: MutableList<T>, order: Order): MutableList<T> {

        val heapType = when (order) {
            Order.ASC -> HeapType.MIN
            Order.DESC -> HeapType.MAX
        }

        val heap = HeapImpl<T>(heapType)

        while (values.isNotEmpty()) heap.add(values.removeAt(0))
        while (heap.isNotEmpty()) values.add(heap.poll())

        return values
    }
}
