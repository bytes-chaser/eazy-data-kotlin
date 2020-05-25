package com.goldenberg.data.tables.defaultImpl.cell

import com.goldenberg.data.tables.TableFactory
import com.goldenberg.data.tables.WritableTable
import com.goldenberg.data.tables.cell.AbstractTableCellGeneralTest
import com.goldenberg.data.tables.defaultImpl.*
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
    fun `Set Cell Value`() {
        assertEquals("val1", cell.getValue())

        val val2 = "val2"
        val defaultCell = this.cell as DefaultCell
        defaultCell.setValue(val2)

        assertEquals(val2, cell.getValue())
    }

    @Test
    override fun `Get Cell Column`() {
        assertEquals("col1", cell.getColumn().getName())
    }

    @Test
    override fun `Is Has Value Test Number`() {
        val defaultCell = cell as DefaultCell
        defaultCell.setValue(5)
        assertTrue { cell.hasValue() }
    }

    @Test
    override fun `Is Has Value Test With Null`() {
        val defaultCell = cell as DefaultCell
        defaultCell.setValue(null)
        assertFalse { cell.hasValue() }
    }

    @Test
    override fun `Is Has Value Test With String`() {
        assertTrue { cell.hasValue() }
    }

    @Test
    override fun `Is Has Value Test With Any`() {
        val defaultCell = cell as DefaultCell
        defaultCell.setValue(Any())
        assertTrue { cell.hasValue() }
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
    override fun `Compare Cell HashCode All Same`() {
        val table = tableFactory.createTable("name")
        val row = tableFactory.createRow(table, 0)
        val col = tableFactory.createColumn(table, "col1")
        val cell = tableFactory.createCell(table, row, col, "val1")
        assertEquals(this.cell.hashCode(), cell.hashCode())
    }

    @Test
    override fun `Compare Cell HashCode Different Table`() {
        val table = tableFactory.createTable("name1")
        val row = tableFactory.createRow(table, 0)
        val col = tableFactory.createColumn(table, "col1")
        val cell = tableFactory.createCell(table, row, col, "val1")
        assertNotEquals(this.cell.hashCode(), cell.hashCode())
    }

    @Test
    override fun `Compare Cell HashCode Different Row`() {
        val table = tableFactory.createTable("name")
        val row = tableFactory.createRow(table, 1)
        val col = tableFactory.createColumn(table, "col1")
        val cell = tableFactory.createCell(table, row, col, "val1")
        assertNotEquals(this.cell.hashCode(), cell.hashCode())
    }

    @Test
    override fun `Compare Cell HashCode Different Column`() {
        val table = tableFactory.createTable("name")
        val row = tableFactory.createRow(table, 0)
        val col = tableFactory.createColumn(table, "col2")
        val cell = tableFactory.createCell(table, row, col, "val1")
        assertNotEquals(this.cell.hashCode(), cell.hashCode())
    }

    @Test
    override fun `Compare Cell HashCode Different Value`() {
        val table = tableFactory.createTable("name")
        val row = tableFactory.createRow(table, 0)
        val col = tableFactory.createColumn(table, "col1")
        val cell = tableFactory.createCell(table, row, col, "val2")
        assertNotEquals(this.cell.hashCode(), cell.hashCode())
    }

    @Test
    override fun `Compare Cell HashCode All Different`() {
        val table = tableFactory.createTable("name1")
        val row = tableFactory.createRow(table, 1)
        val col = tableFactory.createColumn(table, "col2")
        val cell = tableFactory.createCell(table, row, col, "val2")
        assertNotEquals(this.cell.hashCode(), cell.hashCode())
    }

    @Test
    override fun `Compare Same Value Cell`() {
        val table = DefaultTable("tbl")
        val column = DefaultColumn(table, "col")
        val row = DefaultRow(table, 0)
        table.addColumn(column)
        table.addRow(row)
        //TODO move test assertEquals(0, cell.compareTo(tableFactory.createCell(table, row, column, "val1")))
    }

    @Test
    override fun `Compare Different Value Cell`() {
        val table = DefaultTable("tbl")
        val column = DefaultColumn(table, "col")
        val row = DefaultRow(table, 0)
        table.addColumn(column)
        table.addRow(row)
        //TODO move test assertEquals(-1, cell.compareTo(tableFactory.createCell(table, row, column, Any())))
    }

    override fun `Compare With No Comparable`() {
        val table = DefaultTable("tbl")
        val column = DefaultColumn(table, "col")
        val row = DefaultRow(table, 0)
        table.addColumn(column)
        table.addRow(row)
        //TODO move test assertEquals(0, cell.compareTo(tableFactory.createCell(table, row, column, "val1")))
    }

    override fun setTableFactory(): TableFactory {
        return DefaultTableFactory.instance
    }

}
