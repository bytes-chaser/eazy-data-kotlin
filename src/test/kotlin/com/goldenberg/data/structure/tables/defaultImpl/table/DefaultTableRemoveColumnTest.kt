package com.goldenberg.data.structure.tables.defaultImpl.table

import com.goldenberg.data.structure.tables.TableFactory
import com.goldenberg.data.structure.tables.defaultImpl.DefaultColumn
import com.goldenberg.data.structure.tables.defaultImpl.DefaultRow
import com.goldenberg.data.structure.tables.defaultImpl.DefaultTableFactory
import com.goldenberg.data.structure.tables.table.AbstractTableRemoveColumnTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class DefaultTableRemoveColumnTest: AbstractTableRemoveColumnTest() {

    override fun setTableFactory(): TableFactory {
        return DefaultTableFactory.instance
    }

    @BeforeEach
    override fun setup() {
        super.setup()
        tableFactory.createColumn(table,"col1")
        tableFactory.createColumn(table,"col2")
    }

    @Test
    override fun `Remove Existed Column By Column`() {
        val name = "col1"
        assertEquals(2, table.getColumnSize())
        assertNotNull(table.getColumn(name))
        assertEquals(DefaultColumn(table, "col1"), table.removeColumn(DefaultColumn(table, "col1")))
        assertEquals(1, table.getColumnSize())
        assertNull(table.getColumn(name))
    }

    @Test
    override fun `Remove Not-Existed Column By Index`() {
        val name = "col1"
        assertEquals(2, table.getColumnSize())
        assertNotNull(table.getColumn(name))
        assertEquals(DefaultColumn(table,"col1"), table.removeColumn(name))
        assertEquals(1, table.getColumnSize())
        assertNull(table.getColumn(name))
    }

    @Test
    override fun `Remove Not-Existed Column By Column`() {
        super.setup()
        val name = "col1"
        val col1 = DefaultColumn(table,name)
        val col2 = DefaultColumn(table,"col2")
        table.addColumn(col2)

        assertEquals(table.getColumnSize(), 1)
        assertNull(table.getColumn(name))
        assertNull(table.removeColumn(col1))
        assertEquals(1, table.getColumnSize())
    }

    @Test
    override fun `Remove Column By Column Name`() {
        super.setup()
        val row1 = DefaultRow(table,0)
        val row2 = DefaultRow(table,1)
        val row3 = DefaultRow(table,2)
        val row4 = DefaultRow(table,3)
        table.addRow(row1)
        table.addRow(row2)
        table.addRow(row3)
        table.addRow(row4)

        val col = DefaultColumn(table,"col", true, "default")
        table.addColumn(col)

        assertEquals(table.getCells(col).filter { it.getValue() == "default" }.count(), 4)

        assertEquals(col, table.removeColumn("col"))
        assertEquals(0, table.getCells(col).filter { it.getValue() == "default" }.count())
    }
}
