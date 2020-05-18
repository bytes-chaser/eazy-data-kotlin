package com.goldenberg.data.table

import org.junit.jupiter.api.Test

abstract class AbstractTableRemoveRowTest: AbstractTableTest() {

    @Test
    abstract fun `Remove Existed Row By Index`()

    @Test
    abstract fun `Remove Existed Row By Row`()

    @Test
    abstract fun `Remove Not-Existed Row By Index`()

    @Test
    abstract fun `Remove Existed Row By Outbound Index`()

    @Test
    abstract fun `Remove Not-Existed Row By Row`()

    @Test
    abstract fun `Remove Rows Collection`()

    @Test
    abstract fun `Remove Rows Array`()

    @Test
    abstract fun `Remove Rows No Args`()

    @Test
    abstract fun `Remove Rows Custom Start Index`()

    @Test
    abstract fun `Remove Rows Custom Outbound Start Index`()

    @Test
    abstract fun `Remove Rows Custom End Index`()

    @Test
    abstract fun `Remove Rows Custom Outbound End Index`()

    @Test
    abstract fun `Remove Rows Custom Start and End Indexes`()
}