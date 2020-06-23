package com.goldenberg.data.structure.tables.column

import com.goldenberg.data.structure.tables.AbstractTableInterfacesTest
import com.goldenberg.data.structure.tables.Column
import org.junit.jupiter.api.BeforeEach

abstract class AbstractColumnTest: AbstractTableInterfacesTest() {

    lateinit var col: Column

    @BeforeEach
    override fun setup() {
        super.setup()
        this.col = tableFactory.createColumn(tableFactory.createTable("name"),"col1", true, "def")
    }
}
