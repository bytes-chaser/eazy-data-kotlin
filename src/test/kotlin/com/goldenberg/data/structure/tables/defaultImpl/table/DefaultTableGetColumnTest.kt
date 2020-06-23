package com.goldenberg.data.structure.tables.defaultImpl.table

import com.goldenberg.data.structure.tables.TableFactory
import com.goldenberg.data.structure.tables.defaultImpl.DefaultColumn
import com.goldenberg.data.structure.tables.defaultImpl.DefaultTable
import com.goldenberg.data.structure.tables.defaultImpl.DefaultTableFactory
import com.goldenberg.data.structure.tables.table.AbstractTableGetColumnTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class DefaultTableGetColumnTest: AbstractTableGetColumnTest() {

    override fun setTableFactory(): TableFactory {
        return DefaultTableFactory.instance
    }


    @BeforeEach
    override fun setup() {
        super.setup()
        tableFactory.createColumn(table, "col1")
        tableFactory.createColumn(table, "col2")
        tableFactory.createColumn(table, "col3")
        tableFactory.createColumn(table, "col4")
    }
    @Test
    override fun `Get Columns Empty`() {
        super.setup()
        assertTrue { table.getColumns().isEmpty() }
    }

    @Test
    override fun `Get Columns One`() {
        super.setup()
        tableFactory.createColumn(table, "col1")

        assertTrue { table.getColumns().size == 1 }
    }

    @Test
    override fun `Get Columns One+`() {
        assertTrue { table.getColumns().size == 4 }
    }

    @Test
    override fun `Get Column By Name`() {
        val name = "col1"
        assertEquals(tableFactory.createColumn(table, name), table.getColumn(name))
    }

    @Test
    override fun `Get Column By Empty String`() {
        val name = ""
        assertNull(table.getColumn(name))
    }

    @Test
    override fun `Get Column By Wrong Name`() {
        val name = "wrongName"
        assertNull(table.getColumn(name))
    }

    @Test
    override fun `Get Column Size`() {
        assertEquals(4, table.getColumnSize())
    }

    @Test
    override fun `Get Column exists If exists`() {
        assertTrue { table.isColumnExists(tableFactory.createColumn(table, "col1")) }
    }

    @Test
    override fun `Get Column exists If not exists`() {
        assertFalse { table.isColumnExists(DefaultColumn(DefaultTable("someId"), "someName")) }
    }

    @Test
    override fun `Get Column by Name exists If exists`() {
        assertTrue { table.isColumnExists("col1") }
    }

    @Test
    override fun `Get Column by Name exists If not exists`() {
        assertFalse { table.isColumnExists("false") }
    }
}
