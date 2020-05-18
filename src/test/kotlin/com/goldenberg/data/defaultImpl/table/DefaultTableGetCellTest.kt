package com.goldenberg.data.defaultImpl.table

import com.goldenberg.data.Column
import com.goldenberg.data.TableFactory
import com.goldenberg.data.defaultImpl.DefaultCellFilter
import com.goldenberg.data.defaultImpl.DefaultTableFactory
import com.goldenberg.data.table.AbstractTableGetCellTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DefaultTableGetCellTest: AbstractTableGetCellTest() {

    private lateinit var col: Column

    override fun setTableFactory(): TableFactory {
        return DefaultTableFactory.instance
    }


    @BeforeEach
    override fun setup() {
        super.setup()
        this.col = tableFactory.createColumn(table, "col", true, "default")
        val row0 = tableFactory.createRow(table, 0)
        val row1 = tableFactory.createRow(table, 1)
        val row2 = tableFactory.createRow(table, 2)
        val row3 = tableFactory.createRow(table, 3)

        tableFactory.createCell(table, row0, col, "0")
        tableFactory.createCell(table, row1, col, "1")
        tableFactory.createCell(table, row2, col, "2")
        tableFactory.createCell(table, row3, col, "3")


    }

    @Test
    override fun `Get Cells By Column`() {
        assertEquals(4, table.getCells(col).count())

    }

    @Test
    override fun `Get Cells Values By Column`() {
        assertEquals(4, table.getCellsValues(col).count())
    }

    @Test
    override fun `Get Cells Values By Column and Indexes`() {
        assertEquals(3, table.getCellsValues(col, 0, 2).count())
    }

    @Test
    override fun `Get Cells Values By Column Name`() {
        assertEquals(4, table.getCellsValues("col").count())
    }

    @Test
    override fun `Get cell by filter`() {
        val filter = DefaultCellFilter()
        filter.addCellPredicate { cell -> (cell.getValue() as String).toInt() == 2 }
        val cells = table.getCells(filter)
        assertEquals(1, cells.size)
        assertEquals(2, (cells.iterator().next().getValue() as String).toInt())

    }

    @Test
    override fun `Get cell by permanently false filter`() {
        val filter = DefaultCellFilter()
        filter.addCellPredicate { cell -> cell.getValue() == "false" }
        val cells = table.getCells(filter)
        assertEquals(0, cells.size)
    }

    @Test
    override fun `Get cell by empty filter`() {
        val filter = DefaultCellFilter()
        filter.addCellPredicate { cell -> cell.getValue() == "false" }
        val cells = table.getCells(filter)
        assertEquals(0, cells.size)
    }

    @Test
    override fun `Get cells by filter`() {
        val filter = DefaultCellFilter()
        filter.addCellPredicate { cell -> (cell.getValue() as String).toInt() >= 2  }
        val cells = table.getCells(filter)
        assertEquals(2, cells.size)
        cells.forEach { assertTrue { (it.getValue() as String).toInt() >= 2  } }
    }

    @Test
    override fun `Get cell by filtering cells & rows`() {
        val filter = DefaultCellFilter()
        filter.addCellPredicate { cell -> (cell.getValue() as String).toInt() >= 2  }
        filter.addRowPredicate { row -> row.getIndex() >2 }
        val cells = table.getCells(filter)
        assertEquals(1, cells.size)
        cells.forEach { assertTrue { (it.getValue() as String).toInt() == 3  } }
    }

    @Test
    override fun `Get Cells Values By Column Name and Indexes`() {
        assertEquals(3, table.getCellsValues("col", 0, 2).count())
    }

    @Test
    override fun `Get Cells Values By Non-Existed Column Name and Indexes`() {
        assertEquals(0, table.getCellsValues("unavailable", 0, 2).count())
    }


    @Test
    override fun `Get Cells Custom Start Index`() {
        assertEquals(3, table.getCells(col, 1).count())
    }

    @Test
    override fun `Get Cells Custom Outbound Start Index`() {
        assertThrows<IllegalArgumentException> { table.getCells(col, 4) }
    }

    @Test
    override fun `Get Cells Custom End Index`() {
        assertEquals(3, table.getCells(col, endIndex = 2).count())
    }

    @Test
    override fun `Get Cells Custom Outbound End Index`() {
        assertThrows<IllegalArgumentException> { table.getCells(col, endIndex = 32) }
    }

    @Test
    override fun `Get Cells Custom Start and End Indexes`() {

        assertEquals(2, table.getCells(col, 1, 2).count())
    }

    @Test
    override fun `Get Cells By Column Name`() {
        assertEquals(4, table.getCells("col").count())
    }
}