package com.goldenberg.data.defaultImpl.row

import com.goldenberg.data.Column
import com.goldenberg.data.Table
import com.goldenberg.data.TableFactory
import com.goldenberg.data.WritableTable
import com.goldenberg.data.defaultImpl.DefaultTableFactory
import com.goldenberg.data.row.AbstractTableRowAddCellTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DefaultTableRowAddCellTest: AbstractTableRowAddCellTest() {

    lateinit var table: Table
    lateinit var col: Column

    @BeforeEach
    override fun setup() {
        super.setup()
        this.table = row.getTable()
        this.col = tableFactory.createColumn(table as WritableTable, "col1", true)
    }

    @Test
    override fun `Add Cell By via Column`() {
        row[col] = tableFactory.createCell(table as WritableTable, row, col, "val1")
        assertEquals("val1", row[col]!!.getValue())
    }

    @Test
    override fun `Add Cell By via Column By Operator`() {
        val table2 = tableFactory.createTable("id")
        val row2 = tableFactory.createRow(table2, 0)
        val col2 = tableFactory.createColumn(table2, "col1")
        row[col2] = tableFactory.createCell(table2, row2, col2, "val1")

        assertEquals("val1", row[col]!!.getValue())
    }

    override fun setTableFactory(): TableFactory {
       return DefaultTableFactory.instance
    }
}