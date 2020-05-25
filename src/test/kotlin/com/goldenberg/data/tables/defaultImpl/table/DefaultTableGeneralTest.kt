package com.goldenberg.data.tables.defaultImpl.table

import com.goldenberg.data.tables.TableFactory
import com.goldenberg.data.tables.defaultImpl.DefaultRow
import com.goldenberg.data.tables.defaultImpl.DefaultTable
import com.goldenberg.data.tables.defaultImpl.DefaultTableFactory
import com.goldenberg.data.tables.table.AbstractTableGeneralTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class DefaultTableGeneralTest: AbstractTableGeneralTest(){

    @BeforeEach
    override fun setup() {
        super.setup()
        val col = tableFactory.createColumn(table, "col1")
        val col1 = tableFactory.createColumn(table, "col2")
        val row0 = tableFactory.createRow(table, 0)
        val row1 = tableFactory.createRow(table, 1)
        val row2 = tableFactory.createRow(table, 2)

        tableFactory.createCell(table, row0, col, 0)
        tableFactory.createCell(table, row1, col, 1)
        tableFactory.createCell(table, row2, col, 2)

        tableFactory.createCell(table, row0, col1, 0)
        tableFactory.createCell(table, row1, col1, 1)
        tableFactory.createCell(table, row2, col1, 2)
    }

    @Test
    override fun `Table Contains Test`() {
        tableFactory.createRow(table, 0)
        assertTrue { table.contains(DefaultRow(table, 0)) }
    }

    @Test
    override fun `Table Contains No Match Test`() {
        assertFalse { table.contains(DefaultRow(table, 3)) }
    }

    @Test
    override fun `Table Contains All Test`() {
        tableFactory.createRow(table, 0)
        tableFactory.createRow(table, 1)
        assertTrue { table.containsAll(listOf(DefaultRow(table, 0), DefaultRow(table, 1))) }
    }

    @Test
    override fun `Table Contains All Lack Of Matches Test`() {
        assertFalse { table.containsAll(listOf(DefaultRow(table, 0), DefaultRow(table, 4))) }
    }

    @Test
    override fun `Table Contains All No Matches Test`() {
        assertFalse { table.containsAll(listOf(DefaultRow(table, 3), DefaultRow(table, 4))) }
    }

    @Test
    override fun `Print toString() Test`() {
        assertEquals(table.toString(),
            "col1 ( comparable, default: null ), col2 ( comparable, default: null )\n" +
                "=======================================================================\n" +
                "0, 0\n" +
                "1, 1\n" +
                "2, 2")
    }

    @Test
    override fun `Compare Table HashCode All Same`() {
        val table = tableFactory.createTable("table")
        val col = tableFactory.createColumn(table, "col1")
        val col1 = tableFactory.createColumn(table, "col2")
        val row0 = tableFactory.createRow(table, 0)
        val row1 = tableFactory.createRow(table, 1)
        val row2 = tableFactory.createRow(table, 2)

        tableFactory.createCell(table, row0, col, 0)
        tableFactory.createCell(table, row1, col, 1)
        tableFactory.createCell(table, row2, col, 2)

        tableFactory.createCell(table, row0, col1, 0)
        tableFactory.createCell(table, row1, col1, 1)
        tableFactory.createCell(table, row2, col1, 2)

        assertEquals(this.table.hashCode(), table.hashCode())
    }

    @Test
    override fun `Compare Table HashCode Different ID`() {
        val table = tableFactory.createTable("table1")
        val col = tableFactory.createColumn(table, "col1")
        val col1 = tableFactory.createColumn(table, "col2")
        val row0 = tableFactory.createRow(table, 0)
        val row1 = tableFactory.createRow(table, 1)
        val row2 = tableFactory.createRow(table, 2)

        tableFactory.createCell(table, row0, col, 0)
        tableFactory.createCell(table, row1, col, 1)
        tableFactory.createCell(table, row2, col, 2)

        tableFactory.createCell(table, row0, col1, 0)
        tableFactory.createCell(table, row1, col1, 1)
        tableFactory.createCell(table, row2, col1, 2)

        assertNotEquals(this.table.hashCode(), table.hashCode())
    }

    @Test
    override fun `Compare Table HashCode Different Columns`() {
        val table = tableFactory.createTable("table")
        val col = tableFactory.createColumn(table, "col3")
        val col1 = tableFactory.createColumn(table, "col2")
        val row0 = tableFactory.createRow(table, 0)
        val row1 = tableFactory.createRow(table, 1)
        val row2 = tableFactory.createRow(table, 2)

        tableFactory.createCell(table, row0, col, 0)
        tableFactory.createCell(table, row1, col, 1)
        tableFactory.createCell(table, row2, col, 2)

        tableFactory.createCell(table, row0, col1, 0)
        tableFactory.createCell(table, row1, col1, 1)
        tableFactory.createCell(table, row2, col1, 2)

        assertNotEquals(this.table.hashCode(), table.hashCode())
    }

    @Test
    override fun `Compare Table HashCode Different Rows`() {
        val table = tableFactory.createTable("table")
        val col = tableFactory.createColumn(table, "col1")
        val col1 = tableFactory.createColumn(table, "col2")
        val row0 = tableFactory.createRow(table, 3)
        val row1 = tableFactory.createRow(table, 1)
        val row2 = tableFactory.createRow(table, 2)

        tableFactory.createCell(table, row0, col, 0)
        tableFactory.createCell(table, row1, col, 1)
        tableFactory.createCell(table, row2, col, 2)

        tableFactory.createCell(table, row0, col1, 0)
        tableFactory.createCell(table, row1, col1, 1)
        tableFactory.createCell(table, row2, col1, 2)

        assertNotEquals(this.table.hashCode(), table.hashCode())
    }

    @Test
    override fun `Compare Table HashCode All Different`() {
        val table = tableFactory.createTable("table1")
        val col = tableFactory.createColumn(table, "col3")
        val col1 = tableFactory.createColumn(table, "col2")
        val row0 = tableFactory.createRow(table, 3)
        val row1 = tableFactory.createRow(table, 1)
        val row2 = tableFactory.createRow(table, 2)

        tableFactory.createCell(table, row0, col, 0)
        tableFactory.createCell(table, row1, col, 1)
        tableFactory.createCell(table, row2, col, 2)

        tableFactory.createCell(table, row0, col1, 0)
        tableFactory.createCell(table, row1, col1, 1)
        tableFactory.createCell(table, row2, col1, 2)

        assertNotEquals(this.table.hashCode(), table.hashCode())
    }

    @Suppress("UseWithIndex")
    @Test
    override fun `Table Iterating Test`() {
        var i = 0
        for (row in table) {
            assertEquals(i, row.getIndex())
            i++
        }
    }

    @Test
    fun `Set Id Test`() {
        assertEquals("table", table.getId())

        val defaultTable = table as DefaultTable
        defaultTable.setId("table2")

        assertEquals("table2", table.getId())
    }

    override fun setTableFactory(): TableFactory {
       return DefaultTableFactory.instance
    }
}
