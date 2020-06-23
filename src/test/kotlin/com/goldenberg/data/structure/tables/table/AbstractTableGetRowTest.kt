package com.goldenberg.data.structure.tables.table

import org.junit.jupiter.api.Test

abstract class AbstractTableGetRowTest: AbstractTableTest() {

    @Test
    abstract fun `Get Rows Empty`()

    @Test
    abstract fun `Get Rows One`()

    @Test
    abstract fun `Get Rows One+`()

    @Test
    abstract fun `Get Rows`()

    @Test
    abstract fun `Get Row By Index`()

    @Test
    abstract fun `Get Row By Outbound Index`()

    @Test
    abstract fun `Get Rows Custom Start Index`()

    @Test
    abstract fun `Get Rows Custom Outbound Start Index`()

    @Test
    abstract fun `Get Rows Custom End Index`()

    @Test
    abstract fun `Get Rows Custom Outbound End Index`()

    @Test
    abstract fun `Get Rows Custom Start and End Indexes`()

    @Test
    abstract fun `Get Row Size`()

    @Test
    abstract fun `Get is empty when empty`()

    @Test
    abstract fun `Get is empty when non empty`()

    @Test
    abstract fun `Get row by filter`()

    @Test
    abstract fun `Get row by permanently false filter`()

    @Test
    abstract fun `Get row by empty filter`()

    @Test
    abstract fun `Get rows by filter`()

    @Test
    abstract fun `Is Row Exists If exists`()

    @Test
    abstract fun `Is Row Exists If not exists`()

}
