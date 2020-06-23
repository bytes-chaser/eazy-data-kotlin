package com.goldenberg.data.structure.tables.defaultImpl.table

import com.goldenberg.data.structure.tables.TableFactory
import com.goldenberg.data.structure.tables.defaultImpl.DefaultRow
import com.goldenberg.data.structure.tables.defaultImpl.DefaultTable
import com.goldenberg.data.structure.tables.defaultImpl.DefaultTableFactory
import com.goldenberg.data.structure.tables.table.AbstractTableAddRowTest
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DefaultTableAddRowTest: AbstractTableAddRowTest() {

    override fun setTableFactory(): TableFactory {
        return DefaultTableFactory.instance
    }

    @Test
    override fun `Add Row Not Null`() {
        tableFactory.createRow(table, 0)

        val tt = DefaultTable("table")
        tt.addRow(DefaultRow(tt, 0))

        assertEquals(tt, table)
    }
}
