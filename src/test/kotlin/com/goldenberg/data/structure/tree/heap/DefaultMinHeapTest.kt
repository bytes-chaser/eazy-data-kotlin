package com.goldenberg.data.structure.tree.heap

import com.goldenberg.data.sorting.getHeapPredicate

class DefaultMinHeapTest : AbstractHeapTest() {

    override fun createHeap(): Heap<Int> = HeapImpl()

    override fun createComparator(): (Int, Int) -> Boolean = getHeapPredicate(HeapType.MIN)


}
