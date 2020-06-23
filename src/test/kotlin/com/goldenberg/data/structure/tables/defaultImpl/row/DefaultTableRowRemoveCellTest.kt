package com.goldenberg.data.structure.tables.defaultImpl.row

import com.goldenberg.data.structure.tables.Column
import com.goldenberg.data.structure.tables.Table
import com.goldenberg.data.structure.tables.TableFactory
import com.goldenberg.data.structure.tables.WritableTable
import com.goldenberg.data.structure.tables.defaultImpl.DefaultColumn
import com.goldenberg.data.structure.tables.defaultImpl.DefaultTable
import com.goldenberg.data.structure.tables.defaultImpl.DefaultTableFactory
import com.goldenberg.data.structure.tables.row.AbstractTableRowRemoveCellTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class DefaultTableRowRemoveCellTest: AbstractTableRowRemoveCellTest() {

    lateinit var table: Table
    lateinit var col: Column

    @BeforeEach
    override fun setup() {
        super.setup()
        this.table = row.getTable()
        this.col = tableFactory.createColumn(table as WritableTable, "col1", true)
        tableFactory.createCell(table as WritableTable, row, col, "val1")
    }

    @Test
    override fun `Remove Cell by Column`() {
        val cell = row.remove(col)
        assertEquals("val1", cell!!.getValue())
        assertEquals(col.getDefaultValue(), row[col]!!.getValue())
    }

    @Test
    override fun `Remove Cell by Same Column of another table`() {
        val col2 = DefaultColumn(DefaultTable("random"), col.getName(), true)
        val cell = row.remove(col2)

        assertEquals("val1", cell!!.getValue())
        assertEquals(col.getDefaultValue(), row[col2]!!.getValue())
    }

    @Test
    override fun `Remove Cell by Not added Column`() {
        val col2 = DefaultColumn(DefaultTable("random"), "col2, true")
        assertNull(row.remove(col2))
    }

    @Test
    override fun `Remove Cell by Column Name`() {
        val cell = row.remove(col.getName())
        assertEquals("val1", cell!!.getValue())
        assertEquals(col.getDefaultValue(), row[col.getName()]!!.getValue())
    }

    @Test
    override fun `Remove Cell by not-existed Column Name`() {
        assertNull(row.remove("random"))
    }

    override fun setTableFactory(): TableFactory {
       return DefaultTableFactory.instance
    }
}
