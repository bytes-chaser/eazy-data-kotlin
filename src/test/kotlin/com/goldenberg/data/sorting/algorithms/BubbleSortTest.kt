package com.goldenberg.data.sorting.algorithms

import com.goldenberg.data.sorting.AbstractSortingTest
import com.goldenberg.data.sorting.Sorting

class BubbleSortTest : AbstractSortingTest() {
    override fun initAlgorithm(): Sorting = BubbleSort()
}