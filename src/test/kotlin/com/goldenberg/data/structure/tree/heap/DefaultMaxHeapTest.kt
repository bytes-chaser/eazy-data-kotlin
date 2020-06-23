package com.goldenberg.data.structure.tree.heap

import com.goldenberg.data.sorting.getHeapPredicate

class DefaultMaxHeapTest : AbstractHeapTest() {

    override fun createHeap(): Heap<Int> = HeapImpl(HeapType.MAX)

    override fun createComparator(): (Int, Int) -> Boolean = getHeapPredicate(HeapType.MAX)
}
