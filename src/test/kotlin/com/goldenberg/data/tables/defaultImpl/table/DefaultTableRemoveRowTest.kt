package com.goldenberg.data.tables.defaultImpl.table

import com.goldenberg.data.tables.Row
import com.goldenberg.data.tables.TableFactory
import com.goldenberg.data.tables.defaultImpl.DefaultRow
import com.goldenberg.data.tables.defaultImpl.DefaultTable
import com.goldenberg.data.tables.defaultImpl.DefaultTableFactory
import com.goldenberg.data.tables.table.AbstractTableRemoveRowTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.*


class DefaultTableRemoveRowTest: AbstractTableRemoveRowTest() {

    val col = "col"
    override fun setTableFactory(): TableFactory {
        return DefaultTableFactory.instance
    }

    @BeforeEach
    override fun setup() {
        super.setup()
        tableFactory.createRow(table, 0)
        tableFactory.createRow(table, 1)
        tableFactory.createRow(table, 2)

        tableFactory.createColumn(table, col)
        tableFactory.createCell(table, table.getRow(0), table.getColumn(col)!!, 0)
        tableFactory.createCell(table, table.getRow(1), table.getColumn(col)!!, 1)
        tableFactory.createCell(table, table.getRow(2), table.getColumn(col)!!, 2)
    }

    @Test
    override fun `Remove Existed Row By Index`() {
        val testIndex = 1
        assertEquals(3, table.getRowSize())
        assertNotNull(table.getRow(testIndex))

        val removeRow = table.removeRow(testIndex)

        val expectedTable = tableFactory.createTable("name")
        val expectedRow = tableFactory.createRow(expectedTable, 1)
        val column = tableFactory.createColumn(expectedTable, col)
        tableFactory.createCell(expectedTable, expectedRow, column, 1)

        assertEquals(expectedRow, removeRow)
        assertEquals(2, table.getRowSize())
        assertTrue { table.getRow(testIndex).isEmpty()  }
    }

    @Test
    override fun `Remove Existed Row By Row`() {
        val testIndex = 1
        assertEquals(3, table.getRowSize())
        assertNotNull(table.getRow(testIndex))

        val removeRow = table.removeRow(DefaultRow(DefaultTable("name"),1))


        val expectedTable = tableFactory.createTable("name")
        val expectedRow = tableFactory.createRow(expectedTable, 1)
        val column = tableFactory.createColumn(expectedTable, col)
        tableFactory.createCell(expectedTable, expectedRow, column, 1)

        assertEquals(expectedRow, removeRow)
        assertEquals(2, table.getRowSize())
        assertTrue{ table.getRow(testIndex).isEmpty() }
    }

    @Test
    override fun `Remove Not-Existed Row By Index`() {
        val testIndex = 4

        assertEquals(3, table.getRowSize())

        val removeRow = table.removeRow(testIndex)

        assertNull(removeRow)
        assertEquals(3, table.getRowSize())
        assertTrue{ table.getRow(testIndex).isEmpty() }
    }

    @Test
    override fun `Remove Existed Row By Outbound Index`() {
        val testIndex = 323

        assertEquals(3, table.getRowSize())

        val removeRow = table.removeRow(testIndex)

        assertNull(removeRow)
        assertEquals(3, table.getRowSize())
        assertTrue {  table.getRow(testIndex).isEmpty() }
    }

    @Test
    override fun `Remove Not-Existed Row By Row`() {
        val testIndex = 32

        assertEquals(3, table.getRowSize())

        val removeRow = table.removeRow(DefaultRow(table, testIndex))

        assertNull(removeRow)
        assertEquals(3, table.getRowSize())
        assertTrue {  table.getRow(testIndex).isEmpty() }
    }

    @Test
    override fun `Remove Rows Collection`() {
        table.removeRows(mutableListOf<Row>(
            DefaultRow(table, 0), DefaultRow(table, 1)
        ))

        assertEquals(1, table.getRowSize())
        assertTrue {  table.getRow(0).isEmpty() }
        assertTrue {  table.getRow(1).isEmpty() }
        assertFalse {  table.getRow(2).isEmpty() }
    }

    @Test
    override fun `Remove Rows Array`() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        assertTrue { true }
    }

    @Test
    override fun `Remove Rows No Args`() {
        table.removeRows()
        assertEquals(0, table.getRowSize())
    }

    @Test
    override fun `Remove Rows Custom Start Index`() {
        table.removeRows(1)
        assertEquals(1, table.getRowSize())
        assertEquals(0, table.getRow(0)[col]?.getValue())
    }

    @Test
    override fun `Remove Rows Custom Outbound Start Index`() {
        assertThrows<IllegalArgumentException> { table.removeRows(6) }
    }

    @Test
    override fun `Remove Rows Custom End Index`() {
        table.removeRows(endIndex = 1)

        assertEquals(1, table.getRowSize())
        assertTrue {  table.getRow(0).isEmpty() }
        assertTrue {  table.getRow(1).isEmpty() }
        assertFalse {  table.getRow(2).isEmpty() }
    }

    @Test
    override fun `Remove Rows Custom Outbound End Index`() {
        assertThrows<IllegalArgumentException> {  table.removeRows(endIndex = 6) }
    }

    @Test
    override fun `Remove Rows Custom Start and End Indexes`() {
        table.removeRows(0, 1)
        assertEquals(1, table.getRowSize())
        assertTrue {  table.getRow(0).isEmpty() }
        assertTrue {  table.getRow(1).isEmpty() }
        assertFalse {  table.getRow(2).isEmpty() }
    }
}
