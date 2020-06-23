package com.goldenberg.data.structure.tables.defaultImpl.table

import com.goldenberg.data.structure.tables.Column
import com.goldenberg.data.structure.tables.TableFactory
import com.goldenberg.data.structure.tables.defaultImpl.DefaultColumn
import com.goldenberg.data.structure.tables.defaultImpl.DefaultTable
import com.goldenberg.data.structure.tables.defaultImpl.DefaultTableFactory
import com.goldenberg.data.structure.tables.table.AbstractTableAddColumnTest
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class DefaultTableAddColumnTest: AbstractTableAddColumnTest() {

    override fun setTableFactory(): TableFactory {
        return DefaultTableFactory.instance
    }

    @Test
    override fun `Add Column Not Null`() {
        tableFactory.createColumn(table,"name")

        val tt = DefaultTable("table")
        tt.addColumn(DefaultColumn(tt, "name"))

        assertEquals(tt, table)
    }

    @Test
    override fun `Add Columns By Collection`() {
        val col1 = tableFactory.createColumn(table, "col1")
        val col2 = tableFactory.createColumn(table, "col2")

        table.addColumns(mutableListOf(col1, col2))

        assertEquals(2, table.getColumnSize())
        assertNotNull(table.getColumn("col1"))
        assertNotNull(table.getColumn("col2"))
    }

    @Test
    override fun `Add Columns By Array`() {
        val col1: Column = tableFactory.createColumn(table, "col1")
        val col2: Column = tableFactory.createColumn(table, "col2")

        table.addColumns(arrayOf(col1, col2))

        assertEquals(2, table.getColumnSize())
        assertNotNull(table.getColumn("col1"))
        assertNotNull(table.getColumn("col2"))
    }


}
