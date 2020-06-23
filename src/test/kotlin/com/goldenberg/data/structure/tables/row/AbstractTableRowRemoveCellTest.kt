package com.goldenberg.data.structure.tables.row

abstract class AbstractTableRowRemoveCellTest: AbstractRowTest() {

    abstract fun `Remove Cell by Column`()

    abstract fun `Remove Cell by Same Column of another table`()

    abstract fun `Remove Cell by Not added Column`()

    abstract fun `Remove Cell by Column Name`()

    abstract fun `Remove Cell by not-existed Column Name`()
}
