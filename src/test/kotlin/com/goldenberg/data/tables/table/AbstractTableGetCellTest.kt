package com.goldenberg.data.tables.table

import org.junit.jupiter.api.Test

abstract class AbstractTableGetCellTest : AbstractTableTest() {

    @Test
    abstract fun `Get Cells By Column`()

    @Test
    abstract fun `Get Cells Values By Column and Indexes`()

    @Test
    abstract fun `Get Cells Values By Column Name and Indexes`()

    @Test
    abstract fun `Get Cells Values By Non-Existed Column Name and Indexes`()

    @Test
    abstract fun `Get Cells Custom Start Index`()

    @Test
    abstract fun `Get Cells Custom Outbound Start Index`()

    @Test
    abstract fun `Get Cells Custom End Index`()

    @Test
    abstract fun `Get Cells Custom Outbound End Index`()

    @Test
    abstract fun `Get Cells Custom Start and End Indexes`()

    @Test
    abstract fun `Get Cells By Column Name`()

    @Test
    abstract fun `Get Cells Values By Column`()

    @Test
    abstract fun `Get Cells Values By Column Name`()

    @Test
    abstract fun `Get cell by filter`()

    @Test
    abstract fun `Get cell by permanently false filter`()

    @Test
    abstract fun `Get cell by empty filter`()

    @Test
    abstract fun `Get cells by filter`()

    @Test
    abstract fun `Get cell by filtering cells & rows`()
}
