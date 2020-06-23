package com.goldenberg.data.structure.tables.defaultImpl.table

import com.goldenberg.data.structure.tables.TableFactory
import com.goldenberg.data.structure.tables.defaultImpl.DefaultRow
import com.goldenberg.data.structure.tables.defaultImpl.DefaultRowFilter
import com.goldenberg.data.structure.tables.defaultImpl.DefaultTableFactory
import com.goldenberg.data.structure.tables.table.AbstractTableGetRowTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DefaultTableGetRowTest: AbstractTableGetRowTest() {

    override fun setTableFactory(): TableFactory {
        return DefaultTableFactory.instance
    }

    @BeforeEach
    override fun setup() {
        super.setup()
        tableFactory.createRow(table, 0)
        tableFactory.createRow(table, 1)
        tableFactory.createRow(table, 2)
    }

    @Test
    override fun `Get Rows Empty`() {
        super.setup()
        assertTrue { table.getRows().isEmpty() }
    }

    @Test
    override fun `Get Rows One`() {
        super.setup()
        tableFactory.createRow(table, 0)

        assertTrue { table.getRows().size == 1 }
    }

    @Test
    override fun `Get Rows One+`() {
        assertTrue { table.getRows().size == 3 }
    }

    @Test
    override fun `Get Row By Index`() {
        assertEquals(DefaultRow(table, 1), table.getRow(1))
    }

    @Test
    override fun `Get Row By Outbound Index`() {
        assertTrue {  table.getRow(3).isEmpty() }
    }

    @Test
    override fun `Get Rows`() {
        assertEquals(3, table.getRows().size)
    }

    @Test
    override fun `Get Rows Custom Start Index`() {
        val rows = table.getRows(1)
        assertEquals(2, rows.size)
        assertFalse{ rows.contains(DefaultRow(table, 0)) }
        assertTrue { rows.contains(DefaultRow(table, 1)) }
        assertTrue { rows.contains(DefaultRow(table, 2)) }
    }

    @Test
    override fun `Get Rows Custom Outbound Start Index`() {
        assertThrows<IllegalArgumentException> {  table.getRows(13) }
    }

    @Test
    override fun `Get Rows Custom End Index`() {
        val rows = table.getRows(endIndex = 1)
        assertEquals(2, rows.size)
        assertTrue{ rows.contains(DefaultRow(table, 0)) }
        assertTrue { rows.contains(DefaultRow(table, 1)) }
        assertFalse { rows.contains(DefaultRow(table, 2)) }
    }

    @Test
    override fun `Get Rows Custom Outbound End Index`() {
        assertThrows<IllegalArgumentException> {  table.getRows(endIndex = 13) }
    }

    @Test
    override fun `Get Rows Custom Start and End Indexes`() {
        val rows = table.getRows(1, 1)
        assertEquals(1, rows.size)
        assertFalse{ rows.contains(DefaultRow(table, 0)) }
        assertTrue { rows.contains(DefaultRow( table, 1)) }
        assertFalse { rows.contains(DefaultRow(table, 2)) }
    }

    @Test
    override fun `Get Row Size`() {
        assertEquals(3, table.getRowSize())

    }

    @Test
    override fun `Get is empty when empty`() {
        super.setup()
        assertTrue { table.isEmpty() }
    }

    @Test
    override fun `Get is empty when non empty`() {
        assertFalse { table.isEmpty() }
    }

    @Test
    override fun `Get row by filter`() {
        val filter = DefaultRowFilter()
        filter.addRowPredicate { row -> row.getIndex() == 1 }
        val rows = table.getRows(filter)
        assertEquals(1, rows.size)
        assertEquals(1, rows.iterator().next().getIndex())
    }

    @Test
    override fun `Get row by permanently false filter`() {
        val filter = DefaultRowFilter()
        filter.addRowPredicate { row -> row.getIndex() == 5 }
        val rows = table.getRows(filter)
        assertTrue { rows.isEmpty() }
    }

    @Test
    override fun `Get row by empty filter`() {
        val filter = DefaultRowFilter()
        filter.addRowPredicate { row -> row.getIndex() == 5 }
        val rows = table.getRows(filter)
        assertEquals(0, rows.size)
    }

    @Test
    override fun `Get rows by filter`() {
        val filter = DefaultRowFilter()
        filter.addRowPredicate { row -> row.getIndex() >= 1 }
        val rows = table.getRows(filter)
        assertEquals(2, rows.size)
        rows.forEach { assertTrue { it.getIndex() >= 1 } }
    }

    @Test
    override fun `Is Row Exists If exists`() {
        val row = table.getRow(2)
        val col = tableFactory.createColumn(table, "col1")
        tableFactory.createCell(table, row, col, "val")
        assertTrue { table.isRowExists(2) }
    }

    @Test
    override fun `Is Row Exists If not exists`() {
        assertFalse { table.isRowExists(30) }
    }
}
