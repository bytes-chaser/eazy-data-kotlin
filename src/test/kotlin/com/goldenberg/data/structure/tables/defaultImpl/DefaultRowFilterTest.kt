package com.goldenberg.data.structure.tables.defaultImpl

import com.goldenberg.data.structure.tables.AbstractRowFilterTest
import com.goldenberg.data.structure.tables.Row
import org.junit.Assert
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DefaultRowFilterTest : AbstractRowFilterTest() {


    private lateinit var factory: DefaultTableFactory
    private lateinit var filter: DefaultRowFilter

    @BeforeEach
    fun setup() {
        this.factory = DefaultTableFactory.instance
        this.filter = DefaultRowFilter()
    }

    @Test
    override fun `Add Row Predicate Test`() {
        val predicate = { row: Row -> row.getIndex() == 5 }
        filter.addRowPredicate(predicate)

        val table = factory.createTable("id")
        val row1 = factory.createRow(table, 5)
        val row2 = factory.createRow(table, 8)

        assertTrue { predicate.invoke(row1) }
        assertFalse { predicate.invoke(row2) }

    }

    @Test
    override fun `Add Row Predicate By Row Test`() {
        filter.addRowPredicate(DefaultRow(DefaultTable("some"), 5))

        val table = factory.createTable("id")
        val row1 = factory.createRow(table, 5)
        val row2 = factory.createRow(table, 8)

        assertTrue { filter.getRowPredicatesArray()[0].invoke(row1) }
        assertFalse { filter.getRowPredicatesArray()[0].invoke(row2) }
    }

    @Test
    override fun `Remove Row Predicate Test`() {
        val predicate = { row: Row -> row.getIndex() == 5 }
        filter.addRowPredicate(predicate)
        assertTrue { filter.isRowPredicatesExists() }

        filter.removeRowPredicate(predicate)
        assertFalse { filter.isRowPredicatesExists() }

    }

    @Test
    override fun `Remove Row Predicate By Row Test`() {
        val defaultRow = DefaultRow(DefaultTable("some"), 5)
        filter.addRowPredicate(defaultRow)
        assertTrue { filter.isRowPredicatesExists() }

        filter.removeRowPredicate(defaultRow)
        assertFalse { filter.isRowPredicatesExists() }
    }

    @Test
    override fun `Is Row Predicate Exists Test`() {
        assertFalse { filter.isRowPredicatesExists() }
        val predicate = { row: Row -> row.getIndex() == 5 }
        filter.addRowPredicate(predicate)

        assertTrue { filter.isRowPredicatesExists() }
    }

    @Test
    override fun `Get Row Predicates Array Test`() {
        val predicate = { row: Row -> row.getIndex() == 5 }
        val predicate1 = { row: Row -> row.getIndex() == 6 }

        filter.addRowPredicate(predicate)
        filter.addRowPredicate(predicate1)

        val arr = arrayOf(predicate, predicate1)
        Assert.assertArrayEquals(arr, filter.getRowPredicatesArray())
    }

    @Test
    override fun `Get Row Predicates Collection Test`() {
        val predicate = { row: Row -> row.getIndex() == 5 }
        val predicate1 = { row: Row -> row.getIndex() == 6 }

        filter.addRowPredicate(predicate)
        filter.addRowPredicate(predicate1)

        val list = mutableListOf(predicate, predicate1)
        assertEquals(list, filter.getRowPredicatesCollection())
    }


}
