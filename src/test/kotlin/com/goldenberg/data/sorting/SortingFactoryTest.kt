package com.goldenberg.data.sorting

import com.goldenberg.data.enums.SortingAlgorithm
import com.goldenberg.data.sorting.algorithms.*
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class SortingFactoryTest {

    @Test
    fun `Get Bubble Sort Algorithm`() {
        assertTrue { getSorting(SortingAlgorithm.BUBBLE_SORT) is BubbleSort }
    }

    @Test
    fun `Get Heap Sort Algorithm`() {
        assertTrue { getSorting(SortingAlgorithm.HEAP_SORT) is HeapSort }
    }

    @Test
    fun `Get Insertion Sort Algorithm`() {
        assertTrue { getSorting(SortingAlgorithm.INSERTION_SORT) is InsertionSort }
    }

    @Test
    fun `Get Merge Sort Algorithm`() {
        assertTrue { getSorting(SortingAlgorithm.MERGE_SORT) is MergeSort }
    }

    @Test
    fun `Get Quick Sort Algorithm`() {
        assertTrue { getSorting(SortingAlgorithm.QUICK_SORT) is QuickSort }
    }

    @Test
    fun `Get Selection Sort Algorithm`() {
        assertTrue { getSorting(SortingAlgorithm.SELECTION_SORT) is SelectionSort }
    }
}