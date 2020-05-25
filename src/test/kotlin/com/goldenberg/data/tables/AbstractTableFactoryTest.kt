package com.goldenberg.data.tables

import com.goldenberg.data.tables.defaultImpl.DefaultColumn
import com.goldenberg.data.tables.defaultImpl.DefaultRow
import com.goldenberg.data.tables.defaultImpl.DefaultTable
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class AbstractTableFactoryTest{

    private var defaultRowId: Int = 0
    private lateinit var factory: TableFactory

    private lateinit var defaultTableId: String
    private lateinit var defaultColumnName: String
    private lateinit var defaultCellValue: Any

    @BeforeAll
    fun setup() {
        println(">> Setup")
        this.factory = setFactory()
        this.defaultTableId = "table"
        this.defaultRowId = 1
        this.defaultColumnName = "name"
        this.defaultCellValue = "value"
    }

    abstract fun setFactory(): TableFactory


    @Test
    fun `Create Table` () {
        assertEquals(setTbl(defaultTableId), factory.createTable(defaultTableId))
    }

    @Test
    fun `Create Row` () {
        val table = factory.createTable(defaultTableId)
        assertEquals(setRow(defaultRowId, table), factory.createRow(table, defaultRowId))
    }

    @Test
    fun `Create Row By Row` () {
        val table = factory.createTable(defaultTableId)
        assertEquals(setRow(defaultRowId, table), factory.createRow(table, defaultRowId))
    }

    @Test
    fun `Create Row By Row Override index` () {
        val table = factory.createTable(defaultTableId)
        assertEquals(setRow(defaultRowId, DefaultTable(defaultTableId)), factory.createRow(table, DefaultRow(table, 0), defaultRowId))
    }

    @Test
    fun `Create Row By Row Override Exception` () {
        val table = factory.createTable(defaultTableId)
        val row = factory.createRow(table, defaultRowId)
        assertThrows<IllegalArgumentException> { factory.createRow(table, row) }
    }

    @Test
    fun `Create Row By Row Override Row`() {
        val table = factory.createTable(defaultTableId)
        val row = factory.createRow(table, defaultRowId)
        assertEquals(setRow(defaultRowId, table), factory.createRow(table, row, isOverride = true))
    }

    @Test
    fun `Create Row By Row Override Row With New Columns`() {
        val table = factory.createTable(defaultTableId)
        val col = factory.createColumn(table, defaultColumnName)
        val col1 = factory.createColumn(table, defaultColumnName + "1")
        val row = factory.createRow(table, defaultRowId)
        factory.createCell(table, row, col, "val")
        factory.createCell(table, row, col1, "val1")

        val table1 = factory.createTable(defaultTableId)
        factory.createColumn(table1, defaultColumnName)
        factory.createRow(table1, defaultRowId)

        val table2 = factory.createTable(defaultTableId)
        val col2 = factory.createColumn(table2, defaultColumnName)
        val row2 = factory.createRow(table2, defaultRowId)
        factory.createCell(table2, row2, col2, "val")

        assertEquals(row2, factory.createRow(table1, row, isOverride = true))
    }

    @Test
    fun `Create Row By Row Override Index & Allow Override`() {
        val table = factory.createTable(defaultTableId)
        val row = factory.createRow(table, 0)
        assertEquals(setRow(defaultRowId, table), factory.createRow(table, row, defaultRowId, true))
    }

    @Test
    fun `Create Column`() {
        val table = factory.createTable(defaultTableId)
        assertEquals(setCol(this.defaultColumnName, table), factory.createColumn(table, this.defaultColumnName))
    }

    @Test
    fun `Create Column By Column` () {
        val table = factory.createTable(defaultTableId)
        val tbl = DefaultTable(defaultTableId)
        val col = DefaultColumn(tbl, defaultColumnName)
        assertEquals(setCol(this.defaultColumnName, table), factory.createColumn(table, col))
    }

    @Test
    fun `Create Column With No Comparable Value`() {
        val table = factory.createTable(defaultTableId)
        assertEquals(setCol(this.defaultColumnName, table, false), factory.createColumn(table, this.defaultColumnName, false))
    }

    @Test
    fun `Create Column With Default Value`() {
        val table = factory.createTable(defaultTableId)
        val col = factory.createColumn(table, defaultColumnName, defaultValue = defaultCellValue)
        assertEquals(col, setCol(defaultColumnName, table, defaultValue = defaultCellValue))
    }


    @Test
    fun `Create Column With Non-Comparable Default Value`() {
        val table = factory.createTable(defaultTableId)
        val col = factory.createColumn(table, defaultColumnName, false, defaultCellValue)
        assertEquals(setCol(defaultColumnName, table, false, defaultCellValue), col)
    }


    @Test
    fun `Create Cell`() {
        val table = factory.createTable(defaultTableId)
        val row = factory.createRow(table, defaultRowId)
        val col = factory.createColumn(table, defaultColumnName)
        val cell = factory.createCell(table, row, col, defaultCellValue)
        val tbl = setTbl(defaultTableId)
        val col1 = setCol(defaultColumnName, tbl)
        val row1 = setRow(defaultRowId, tbl)
        val cell1 = setCell(defaultCellValue, tbl, row1, col1)
        assertEquals(cell1, cell)
    }

    @Test
    fun `Create Cell With Override`() {
        val table = factory.createTable(defaultTableId)
        val row = factory.createRow(table, defaultRowId)
        val col = factory.createColumn(table, defaultColumnName)
        val cell = factory.createCell(table, row, col, defaultCellValue)

        val table1 = factory.createTable(defaultTableId)

        assertEquals(cell, factory.createCell(table1, cell))
    }

    abstract fun setTbl(id: String): WritableTable

    abstract fun setCol(
            name: String,
            table: WritableTable,
            isComparable: Boolean = true,
            defaultValue: Any? = null
    ): Column

    abstract fun setRow(id: Int, table: WritableTable): Row

    abstract fun setCell(
            value: Any,
            table: WritableTable,
            row: Row,
            column: Column
    ): Cell

}
