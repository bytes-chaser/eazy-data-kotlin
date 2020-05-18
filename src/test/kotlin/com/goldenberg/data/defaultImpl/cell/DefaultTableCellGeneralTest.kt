package com.goldenberg.data.defaultImpl.cell

import com.goldenberg.data.TableFactory
import com.goldenberg.data.WritableTable
import com.goldenberg.data.cell.AbstractTableCellGeneralTest
import com.goldenberg.data.defaultImpl.DefaultColumn
import com.goldenberg.data.defaultImpl.DefaultRow
import com.goldenberg.data.defaultImpl.DefaultTable
import com.goldenberg.data.defaultImpl.DefaultTableFactory
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class DefaultTableCellGeneralTest: AbstractTableCellGeneralTest() {

    @Test
    override fun `Get Cell Value`() {
       assertEquals("val1", cell.getValue())
    }

    @Test
    override fun `Get Cell Column`() {
       assertEquals("col1", cell.getColumn().getName())
    }

    @Test
    override fun `Check Default Value false`() {
        assertFalse { cell.isDefault() }
    }

    @Test
    override fun `Check Default Value true`() {
        val tbl = cell.getTable() as WritableTable
        val col = tableFactory.createColumn(tbl, "col1", defaultValue = "val1")
        this.cell = tableFactory.createCell(tbl, cell.getRow(), col, "val1")
        assertTrue { cell.isDefault() }
    }

    @Test
    override fun `Compare Same Value Cell`() {
        val table = DefaultTable("tbl")
        val column = DefaultColumn(table, "col")
        val row = DefaultRow(table, 0)
        table.addColumn(column)
        table.addRow(row)
        assertEquals(0, cell.compareTo(tableFactory.createCell(table, row, column, "val1")))
    }

    @Test
    override fun `Compare Different Value Cell`() {
        val table = DefaultTable("tbl")
        val column = DefaultColumn(table, "col")
        val row = DefaultRow(table, 0)
        table.addColumn(column)
        table.addRow(row)
        assertNotEquals(0, cell.compareTo(tableFactory.createCell(table, row, column, "val2")))
    }

    override fun setTableFactory(): TableFactory {
        return DefaultTableFactory.instance
    }

}