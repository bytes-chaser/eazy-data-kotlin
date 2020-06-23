package com.goldenberg.data.structure.tables.table

import org.junit.jupiter.api.Test


abstract class AbstractTableAddColumnTest: AbstractTableTest() {

    @Test
    abstract fun `Add Column Not Null`()

    @Test
    abstract fun `Add Columns By Collection`()

    @Test
    abstract fun `Add Columns By Array`()
}
