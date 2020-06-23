package com.goldenberg.data.structure.tables.defaultImpl

import com.goldenberg.data.structure.tables.AbstractCellFilterTest
import com.goldenberg.data.structure.tables.Cell
import com.goldenberg.data.structure.tables.Row

import org.junit.Assert
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DefaultCellFilterTest : AbstractCellFilterTest() {

    private lateinit var factory: DefaultTableFactory
    private lateinit var filter: DefaultCellFilter

    @BeforeEach
    fun setup() {
        this.factory = DefaultTableFactory.instance
        this.filter = DefaultCellFilter()
    }

    @Test
    fun `Create Instance With Row Filter`() {
        val defaultRowFilter = DefaultRowFilter()
        val predicate = { row: Row -> row.getIndex() == 5 }

        defaultRowFilter.addRowPredicate(predicate)
        val defaultCellFilter = DefaultCellFilter(defaultRowFilter)

        Assert.assertArrayEquals(arrayOf(predicate), defaultCellFilter.getRowPredicatesArray())
    }

    @Test
    override fun `Add Cell Predicate Test`() {
        val predicate = { cell: Cell -> cell.getValue() == 5 }
        filter.addCellPredicate(predicate)

        val table = factory.createTable("id")
        val col = factory.createColumn(table, "name")
        val row1 = factory.createRow(table, 5)
        val row2 = factory.createRow(table, 8)
        val cell1 = factory.createCell(table, row1, col, 5)
        val cell2 = factory.createCell(table, row2, col, 8)

        assertTrue { filter.getCellPredicatesArray()[0].invoke(cell1) }
        assertFalse { filter.getCellPredicatesArray()[0].invoke(cell2) }
    }

    @Test
    override fun `Add Cell Predicate By Row Test`() {
        val table = factory.createTable("id")
        val col = factory.createColumn(table, "name")
        val row1 = factory.createRow(table, 5)
        val row2 = factory.createRow(table, 8)
        val cell1 = factory.createCell(table, row1, col, 5)
        val cell2 = factory.createCell(table, row2, col, 8)

        filter.addCellPredicate(cell1)

        assertTrue { filter.getCellPredicatesArray()[0].invoke(cell1) }
        assertFalse { filter.getCellPredicatesArray()[0].invoke(cell2) }
    }

    @Test
    override fun `Remove Cell Predicate Test`() {
        val predicate = { cell: Cell -> cell.getValue() == 5 }
        filter.addCellPredicate(predicate)
        assertTrue { filter.isCellPredicateExists() }

        filter.removeCellPredicate(predicate)
        assertFalse { filter.isCellPredicateExists() }
    }

    @Test
    override fun `Remove Cell Predicate By Cell Test`() {
        val table = factory.createTable("id")
        val col = factory.createColumn(table, "name")
        val row1 = factory.createRow(table, 5)
        val cell1 = factory.createCell(table, row1, col, 5)

        filter.addCellPredicate(cell1)
        assertTrue { filter.isCellPredicateExists() }

        filter.removeCellPredicate(cell1)
        assertFalse { filter.isCellPredicateExists() }
    }

    @Test
    override fun `Is Cell Predicate Exists Test`() {
        assertFalse { filter.isCellPredicateExists() }
        val predicate = { cell: Cell -> cell.getValue() == 5 }
        filter.addCellPredicate(predicate)

        assertTrue { filter.isCellPredicateExists() }
    }

    @Test
    override fun `Get Cell Predicates Array Test`() {
        val predicate = { cell: Cell -> cell.getValue() == 5 }
        val predicate1 = { cell: Cell -> cell.getValue() == 6 }

        filter.addCellPredicate(predicate)
        filter.addCellPredicate(predicate1)

        val arr = arrayOf(predicate, predicate1)
        Assert.assertArrayEquals(arr, filter.getCellPredicatesArray())
    }

    @Test
    override fun `Get Cell Predicates Collection Test`() {
        val predicate = { cell: Cell -> cell.getValue() == 5 }
        val predicate1 = { cell: Cell -> cell.getValue() == 6 }

        filter.addCellPredicate(predicate)
        filter.addCellPredicate(predicate1)

        val list = mutableListOf(predicate, predicate1)
        assertEquals(list, filter.getCellPredicatesCollection())
    }

    @Test
    override fun `Add Row Predicate Test`() {
        val predicate = { row: Row -> row.getIndex() == 5 }
        filter.addRowPredicate(predicate)

        val table = factory.createTable("id")
        val row1 = factory.createRow(table, 5)
        val row2 = factory.createRow(table, 8)

        assertTrue { filter.getRowPredicatesArray()[0].invoke(row1) }
        assertFalse { filter.getRowPredicatesArray()[0].invoke(row2) }

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
