package com.goldenberg.data.tables.table

import org.junit.jupiter.api.Test


abstract class AbstractTableAddColumnTest: AbstractTableTest() {

    @Test
    abstract fun `Add Column Not Null`()

    @Test
    abstract fun `Add Columns By Collection`()

    @Test
    abstract fun `Add Columns By Array`()
}
