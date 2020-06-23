package com.goldenberg.data.structure.tree.heap

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*
import kotlin.collections.ArrayList
import kotlin.test.assertEquals
import kotlin.test.assertTrue

abstract class AbstractHeapTest {

    private lateinit var heap: Heap<Int>
    private lateinit var comparator: (Int, Int) -> Boolean

    @BeforeEach
    fun before() {
        this.heap = createHeap()
        this.comparator = createComparator()
    }

    fun `Single element Add & Poll Test`() {
        assertThrows<IllegalArgumentException> { heap.poll() }
        heap.add(3)
        assertEquals(3, heap.poll())
        assertThrows<IllegalArgumentException> { heap.poll() }
    }

    @Test
    fun `Add Test`() {
        val randomGenerator = Random()

        val randomList = ArrayList<Int>()

        var i = 0
        while (i < 100) {
            randomList.add(randomGenerator.nextInt(100))
            i++
        }

        heap.addAll(randomList)

        val polledList = ArrayList<Int>()
        while (heap.isNotEmpty()) {
            val poll = heap.poll()
            polledList.forEach { assertTrue { comparator.invoke(poll, it) } }
            polledList.add(poll)
        }
    }


    @Test
    fun `Emptiness Test`() {
        assertTrue { heap.isEmpty() }
        heap.add(3)
        assertTrue { heap.isNotEmpty() }
    }

    @Test
    fun `Clear Test`() {
        heap.add(3)
        assertTrue { heap.isNotEmpty() }
        heap.clear()
        assertTrue { heap.isEmpty() }
    }

    abstract fun createHeap(): Heap<Int>

    abstract fun createComparator(): (Int, Int) -> Boolean
}
