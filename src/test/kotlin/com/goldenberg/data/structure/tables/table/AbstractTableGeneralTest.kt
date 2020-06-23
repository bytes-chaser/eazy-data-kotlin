package com.goldenberg.data.structure.tables.table


abstract class AbstractTableGeneralTest: AbstractTableTest() {

    abstract fun `Table Contains Test`()

    abstract fun `Table Contains No Match Test`()

    abstract fun `Table Contains All Test`()

    abstract fun `Table Contains All Lack Of Matches Test`()

    abstract fun `Table Contains All No Matches Test`()

    abstract fun `Print toString() Test`()

    abstract fun `Compare Table HashCode All Same`()

    abstract fun `Compare Table HashCode Different ID`()

    abstract fun `Compare Table HashCode Different Columns`()

    abstract fun `Compare Table HashCode Different Rows`()

    abstract fun `Compare Table HashCode All Different`()

    abstract fun `Table Iterating Test`()
}
