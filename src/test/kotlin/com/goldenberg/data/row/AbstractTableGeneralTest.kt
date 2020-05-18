package com.goldenberg.data.row

abstract class AbstractTableGeneralTest: AbstractRowTest() {

    abstract fun `Check Column Existence By Column`()

    abstract fun `Check Column Existence By Null Column`()

    abstract fun `Check Column Existence By Not-Existing Column`()

    abstract fun `Check Column Existence By Column Name`()

    abstract fun `Check Column Existence By Not-Existing Column Name`()

    abstract fun `Check get Index`()

    abstract fun `Check get Index if null`()

    abstract fun `Check set Index`()

    abstract fun `Check get Table`()

    abstract fun `Check get Table if null`()

    abstract fun `Check is Empty if empty`()

    abstract fun `Check is Empty if not empty`()

}