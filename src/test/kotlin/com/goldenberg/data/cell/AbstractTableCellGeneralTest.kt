package com.goldenberg.data.cell

import org.junit.jupiter.api.Test

abstract class AbstractTableCellGeneralTest: AbstractCellTest() {

    abstract fun `Get Cell Value`()

    abstract fun `Get Cell Column`()

    abstract fun `Compare Same Value Cell`()

    abstract fun `Compare Different Value Cell`()

    abstract fun `Check Default Value false`()
    @Test
    abstract fun `Check Default Value true`()
}