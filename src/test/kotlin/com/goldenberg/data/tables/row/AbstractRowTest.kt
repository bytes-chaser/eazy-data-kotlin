package com.goldenberg.data.tables.row

import com.goldenberg.data.tables.AbstractTableInterfacesTest
import com.goldenberg.data.tables.Row
import org.junit.jupiter.api.BeforeEach


abstract class AbstractRowTest: AbstractTableInterfacesTest() {

    lateinit var row: Row

    @BeforeEach
    override fun setup() {
        super.setup()
        this.row = tableFactory.createRow( tableFactory.createTable("name"), 0)
    }

}
