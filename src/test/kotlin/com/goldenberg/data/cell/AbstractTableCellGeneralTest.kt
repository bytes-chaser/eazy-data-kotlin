package com.goldenberg.data.cell


abstract class AbstractTableCellGeneralTest: AbstractCellTest() {

    abstract fun `Get Cell Value`()

    abstract fun `Get Cell Column`()

    abstract fun `Compare Same Value Cell`()

    abstract fun `Compare Different Value Cell`()

    abstract fun `Check Default Value false`()

    abstract fun `Check Default Value true`()

    abstract fun `Compare Cell HashCode All Same`()

    abstract fun `Compare Cell HashCode Different Table`()

    abstract fun `Compare Cell HashCode Different Row`()

    abstract fun `Compare Cell HashCode Different Column`()

    abstract fun `Compare Cell HashCode Different Value`()

    abstract fun `Compare Cell HashCode All Different`()

}