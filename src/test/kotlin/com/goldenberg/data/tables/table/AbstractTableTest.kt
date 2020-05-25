package com.goldenberg.data.tables.table

import com.goldenberg.data.tables.AbstractTableInterfacesTest
import com.goldenberg.data.tables.WritableTable
import org.junit.jupiter.api.BeforeEach

abstract class AbstractTableTest: AbstractTableInterfacesTest() {

    lateinit var table: WritableTable

    @BeforeEach
    override fun setup() {
        super.setup()
        this.table = tableFactory.createTable("table")
    }

}
