package com.goldenberg.data.tables.cell


abstract class AbstractTableCellGeneralTest: AbstractCellTest() {

    abstract fun `Get Cell Value`()

    abstract fun `Get Cell Column`()

    abstract fun `Is Has Value Test Number`()

    abstract fun `Is Has Value Test With Null`()

    abstract fun `Is Has Value Test With String`()

    abstract fun `Is Has Value Test With Any`()

    abstract fun `Compare Same Value Cell`()

    abstract fun `Compare Different Value Cell`()

    abstract fun `Compare With No Comparable`()

    abstract fun `Check Default Value false`()

    abstract fun `Check Default Value true`()

    abstract fun `Compare Cell HashCode All Same`()

    abstract fun `Compare Cell HashCode Different Table`()

    abstract fun `Compare Cell HashCode Different Row`()

    abstract fun `Compare Cell HashCode Different Column`()

    abstract fun `Compare Cell HashCode Different Value`()

    abstract fun `Compare Cell HashCode All Different`()

}
