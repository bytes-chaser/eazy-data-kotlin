package com.goldenberg.data.tables.cell

import com.goldenberg.data.tables.AbstractTableInterfacesTest
import com.goldenberg.data.tables.Cell
import org.junit.jupiter.api.BeforeEach

abstract class AbstractCellTest: AbstractTableInterfacesTest() {

    lateinit var cell: Cell

    @BeforeEach
    override fun setup() {
        super.setup()
        val table = tableFactory.createTable("name")
        val row = tableFactory.createRow(table, 0)
        val col = tableFactory.createColumn(table, "col1")
        this.cell = tableFactory.createCell(table, row, col, "val1")
    }
}
