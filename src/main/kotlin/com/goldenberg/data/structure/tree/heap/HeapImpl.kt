package com.goldenberg.data.structure.tree.heap

class HeapImpl<T : Comparable<T>>(heapType: HeapType = HeapType.MIN) : AbstractHeap<T>(heapType)
