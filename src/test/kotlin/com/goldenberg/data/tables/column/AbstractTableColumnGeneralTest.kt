package com.goldenberg.data.tables.column


abstract class AbstractTableColumnGeneralTest: AbstractColumnTest() {

    abstract fun `Get Rows Cells`()

    abstract fun `Get Rows Cells Custom Start Index`()

    abstract fun `Get Rows Cells Custom Outbound Start Index`()

    abstract fun `Get Rows Cells Custom End Index`()

    abstract fun `Get Rows Cells Custom Outbound End Index`()

    abstract fun `Get Rows Cells Custom Start and End Indexes`()

    abstract fun `Get Rows Cells Value`()

    abstract fun `Get Rows Cells Values Custom Start Index`()

    abstract fun `Get Rows Cells Values Custom Outbound Start Index`()

    abstract fun `Get Rows Cells Values Custom End Index`()

    abstract fun `Get Rows Cells Values Custom Outbound End Index`()

    abstract fun `Get Rows Cells Values Custom Start and End Indexes`()

    abstract fun `Get Column Name`()

    abstract fun `Get Column Default`()

    abstract fun `Get Column Comparable True`()

    abstract fun `Get Column Table `()

    abstract fun `Print toString() Test`()

    abstract fun `Print toString() Test Non-Comparable`()

    abstract fun `Print toString() Test Null Default`()

    abstract fun `Compare Column HashCode All Same`()

    abstract fun `Compare Column HashCode Different Table`()

    abstract fun `Compare Column HashCode Different Name`()

    abstract fun `Compare Column HashCode Different Comparable`()

    abstract fun `Compare Column HashCode Different Default Value`()

    abstract fun `Compare Column HashCode All Different`()
}
