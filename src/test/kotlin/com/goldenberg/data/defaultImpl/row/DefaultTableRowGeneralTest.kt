package com.goldenberg.data.defaultImpl.row

import com.goldenberg.data.TableFactory
import com.goldenberg.data.WritableTable
import com.goldenberg.data.defaultImpl.DefaultRow
import com.goldenberg.data.defaultImpl.DefaultTableFactory
import com.goldenberg.data.row.AbstractTableRowGeneralTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class DefaultTableRowGeneralTest: AbstractTableRowGeneralTest() {

    @BeforeEach
    override fun setup() {
        super.setup()
        val table = row.getTable() as WritableTable
        val col1 = tableFactory.createColumn(table, "col1")
        val col2 = tableFactory.createColumn(table, "col2")
        tableFactory.createCell(table, row, col1, "val1")
        tableFactory.createCell(table, row, col2, "val2")
    }

    @Test
    override fun `Compare Row HashCode All Same`() {
        val table = tableFactory.createTable(row.getTable().getId())
        val row = tableFactory.createRow(table, row.getIndex())
        val col1 = tableFactory.createColumn(table, "col1")
        val col2 = tableFactory.createColumn(table, "col2")
        tableFactory.createCell(table, row, col1, "val1")
        tableFactory.createCell(table, row, col2, "val2")
        assertEquals(this.row.hashCode(), row.hashCode())
    }

    @Test
    override fun `Compare Row HashCode Different Cells`() {
        val table = tableFactory.createTable(row.getTable().getId())
        val row = tableFactory.createRow(table, row.getIndex())
        val col1 = tableFactory.createColumn(table, "col1")
        val col2 = tableFactory.createColumn(table, "col2")
        tableFactory.createCell(table, row, col1, "val3")
        tableFactory.createCell(table, row, col2, "val4")
        assertEquals(this.row.hashCode(), row.hashCode())
    }

    @Test
    override fun `Compare Row HashCode Different Table`() {
        val table = tableFactory.createTable("random")
        val row = tableFactory.createRow(table, row.getIndex())
        val col1 = tableFactory.createColumn(table, "col1")
        val col2 = tableFactory.createColumn(table, "col2")
        tableFactory.createCell(table, row, col1, "val1")
        tableFactory.createCell(table, row, col2, "val2")
        assertNotEquals(this.row.hashCode(), row.hashCode())
        print(row.toString())
    }

    override fun `Compare Row HashCode Different ID`() {
        val table = tableFactory.createTable(row.getTable().getId())
        val row = tableFactory.createRow(table, 9999)
        val col1 = tableFactory.createColumn(table, "col1")
        val col2 = tableFactory.createColumn(table, "col2")
        tableFactory.createCell(table, row, col1, "val1")
        tableFactory.createCell(table, row, col2, "val2")
        assertNotEquals(this.row.hashCode(), row.hashCode())
    }

    @Test
    override fun `Compare Row HashCode All Different`() {
        val table = tableFactory.createTable("random")
        val row = tableFactory.createRow(table, 9999)
        val col1 = tableFactory.createColumn(table, "col1")
        val col2 = tableFactory.createColumn(table, "col2")
        tableFactory.createCell(table, row, col1, "val3")
        tableFactory.createCell(table, row, col2, "val4")
        assertNotEquals(this.row.hashCode(), row.hashCode())
    }

    @Test
    override fun `Print toString() Test`() {
        assertEquals("val1, val2", row.toString())
    }

    @Test
    fun `Get Map Test`() {
        val table = row.getTable() as WritableTable
        val col1 = tableFactory.createColumn(table, "col1")
        val col2 = tableFactory.createColumn(table, "col2")
        val cell1 = tableFactory.createCell(table, row, col1, "val1")
        val cell2 = tableFactory.createCell(table, row, col2, "val2")

        val map = mapOf("col1" to cell1, "col2" to cell2)
        assertEquals(map, row.getMap())
    }

    @Test
    fun `Set Index Test`() {
        val index = 3
        assertNotEquals(index, row.getIndex())
        val defaultRow = row as DefaultRow
        defaultRow.setIndex(index)
        assertEquals(index, row.getIndex())
    }

    @Test
    fun `Set Table test`() {
        val table = tableFactory.createTable("random")
        assertNotEquals(table, row.getTable())
        val defaultRow = row as DefaultRow
        defaultRow.setTable(table)
        assertEquals(table, row.getTable())
    }

    override fun setTableFactory(): TableFactory {
       return DefaultTableFactory.instance
    }


}