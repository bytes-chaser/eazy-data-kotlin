package com.goldenberg.data.tables.row


abstract class AbstractTableRowGetCellTest: AbstractRowTest() {

    abstract fun `Get Cell By Column`()

    abstract fun `Get Cell By Same Name Column From Another Table`()

    abstract fun `Get Cell By not added Column`()

    abstract fun `Get Cell By Column Name`()

    abstract fun `Get Cell By Non-Existed Column Name`()

    abstract fun `Get Cell By Column By Operator`()

    abstract fun `Get Cell By not added Column By Operator`()

    abstract fun `Get Cell By Same Name Column From Another Table By Operator`()

    abstract fun `Get Cell By Column Name By Operator`()

    abstract fun `Get Cell By Non-Existed Column Name By Operator`()

    abstract fun `Is Row Contains Existed Cell`()

    abstract fun `Is Row Contains Another Cell With Existed Value`()

    abstract fun `Is Row Contains Non-Existed Cell`()

    abstract fun `Is Row Contains Set Of Existed Cells`()

    abstract fun `Is Row Contains Set Of Another Cells With Existed Values`()

    abstract fun `Is Row Contains Non-Existed Cells`()

    abstract fun `Is Row Contains Non-Existed And Existed Cells`()


}
