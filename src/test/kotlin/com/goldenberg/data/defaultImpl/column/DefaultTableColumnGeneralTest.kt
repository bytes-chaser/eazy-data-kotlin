package com.goldenberg.data.defaultImpl.column

import com.goldenberg.data.Table
import com.goldenberg.data.TableFactory
import com.goldenberg.data.WritableTable
import com.goldenberg.data.column.AbstractTableColumnGeneralTest
import com.goldenberg.data.defaultImpl.DefaultTableFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class DefaultTableColumnGeneralTest: AbstractTableColumnGeneralTest() {

    lateinit var table: Table

    @BeforeEach
    override fun setup() {
        super.setup()
        table = this.col.getTable()
        val writableTable = table as WritableTable
        val row0 = tableFactory.createRow(writableTable, 0)
        val row1 = tableFactory.createRow(writableTable, 1)
        val row2 = tableFactory.createRow(writableTable, 2)

        tableFactory.createCell(writableTable, row0, col, "val0")
        tableFactory.createCell(writableTable, row1, col, "val1")
        tableFactory.createCell(writableTable, row2, col, "val2")

    }

    @Test
    override fun `Get Rows Cells`() {
        val rowsCells = col.getRowsCells()!!.map { it.getValue() to it }.toMap()
        assertEquals("val0", rowsCells["val0"]?.getValue())
        assertEquals("val1", rowsCells["val1"]?.getValue())
        assertEquals("val2", rowsCells["val2"]?.getValue())
    }

    @Test
    override fun `Get Rows Cells Custom Start Index`() {
        val rowsCells = col.getRowsCells(startIndex = 1)!!.map { it.getValue() to it }.toMap()
        assertNull(rowsCells["val0"])
        assertEquals("val1", rowsCells["val1"]?.getValue())
        assertEquals("val2", rowsCells["val2"]?.getValue())
    }

    @Test
    override fun `Get Rows Cells Custom Outbound Start Index`() {
        assertThrows<IllegalArgumentException> { col.getRowsCells(startIndex = 6) }
    }

    @Test
    override fun `Get Rows Cells Custom End Index`() {
        val rowsCells = col.getRowsCells(endIndex = 1)!!.map { it.getValue() to it }.toMap()
        assertEquals("val0", rowsCells["val0"]?.getValue())
        assertEquals("val1", rowsCells["val1"]?.getValue())
        assertNull(rowsCells["val2"])
    }

    @Test
    override fun `Get Rows Cells Custom Outbound End Index`() {
        assertThrows<IllegalArgumentException> { col.getRowsCells(endIndex = 6) }
    }

    @Test
    override fun `Get Rows Cells Custom Start and End Indexes`() {
        val rowsCells = col.getRowsCells(startIndex = 1, endIndex = 1)!!.map { it.getValue() to it }.toMap()
        assertNull(rowsCells["val0"])
        assertEquals("val1", rowsCells["val1"]?.getValue())
        assertNull(rowsCells["val2"])
    }

    @Test
    override fun `Get Column Name`() {
        assertEquals("col1", col.getName())
    }

    @Test
    override fun `Get Column Default`() {
        assertEquals("def", col.getDefaultValue())
    }

    @Test
    override fun `Get Column Comparable True`() {
       assertTrue { col.isComparable() }
    }

    @Test
    override fun `Get Column Table `() {
        assertEquals(table, col.getTable())
    }

    override fun setTableFactory(): TableFactory {
        return DefaultTableFactory.instance
    }
}