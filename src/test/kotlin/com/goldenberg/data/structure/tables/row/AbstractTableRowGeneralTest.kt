package com.goldenberg.data.structure.tables.row

abstract class AbstractTableRowGeneralTest: AbstractRowTest() {

    abstract fun `Compare Row HashCode All Same`()

    abstract fun `Compare Row HashCode Different Cells`()

    abstract fun `Compare Row HashCode Different Table`()

    abstract fun `Compare Row HashCode Different ID`()

    abstract fun `Compare Row HashCode All Different`()

    abstract fun `Print toString() Test`()
}
