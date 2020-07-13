package com.goldenberg.data.sorting

import com.goldenberg.data.enums.SortingAlgorithm
import com.goldenberg.data.sorting.SortingAlgorithmsSingletons.bubbleSort
import com.goldenberg.data.sorting.SortingAlgorithmsSingletons.heapSort
import com.goldenberg.data.sorting.SortingAlgorithmsSingletons.insertionSort
import com.goldenberg.data.sorting.SortingAlgorithmsSingletons.mergeSort
import com.goldenberg.data.sorting.SortingAlgorithmsSingletons.quickSort
import com.goldenberg.data.sorting.SortingAlgorithmsSingletons.selectionSort
import com.goldenberg.data.sorting.algorithms.*

internal object SortingAlgorithmsSingletons {
    val bubbleSort: BubbleSort by lazy { BubbleSort() }
    val heapSort: HeapSort by lazy { HeapSort() }
    val insertionSort: InsertionSort by lazy { InsertionSort() }
    val mergeSort: MergeSort by lazy { MergeSort() }
    val quickSort: QuickSort by lazy { QuickSort() }
    val selectionSort: SelectionSort by lazy { SelectionSort() }
}

fun getSorting(sortingAlgorithm: SortingAlgorithm): Sorting = when (sortingAlgorithm) {
    SortingAlgorithm.BUBBLE_SORT -> bubbleSort
    SortingAlgorithm.HEAP_SORT -> heapSort
    SortingAlgorithm.INSERTION_SORT -> insertionSort
    SortingAlgorithm.MERGE_SORT -> mergeSort
    SortingAlgorithm.QUICK_SORT -> quickSort
    SortingAlgorithm.SELECTION_SORT -> selectionSort
}