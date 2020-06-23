package com.goldenberg.data.structure.tables.table

import org.junit.jupiter.api.Test

abstract class AbstractTableGetColumnTest: AbstractTableTest() {

    @Test
    abstract fun `Get Columns Empty`()

    @Test
    abstract fun `Get Columns One`()

    @Test
    abstract fun `Get Columns One+`()

    @Test
    abstract fun `Get Column By Name`()

    @Test
    abstract fun `Get Column By Empty String`()

    @Test
    abstract fun `Get Column By Wrong Name`()

    @Test
    abstract fun `Get Column Size`()

    @Test
    abstract fun `Get Column exists If exists`()

    @Test
    abstract fun `Get Column exists If not exists`()

    @Test
    abstract fun `Get Column by Name exists If exists`()

    @Test
    abstract fun `Get Column by Name exists If not exists`()
}
