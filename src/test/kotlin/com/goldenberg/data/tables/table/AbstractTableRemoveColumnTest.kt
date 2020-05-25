package com.goldenberg.data.tables.table

import org.junit.jupiter.api.Test

abstract class AbstractTableRemoveColumnTest: AbstractTableTest() {

    @Test
    abstract fun `Remove Existed Column By Column`()

    @Test
    abstract fun `Remove Not-Existed Column By Index`()

    @Test
    abstract fun `Remove Not-Existed Column By Column`()

    @Test
    abstract fun `Remove Column By Column Name`()
}
