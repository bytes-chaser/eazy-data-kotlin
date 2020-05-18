package com.goldenberg.data.table

import com.goldenberg.data.AbstractTableInterfacesTest
import com.goldenberg.data.WritableTable
import org.junit.jupiter.api.BeforeEach

abstract class AbstractTableTest: AbstractTableInterfacesTest() {

    lateinit var table: WritableTable

    @BeforeEach
    override fun setup() {
        super.setup()
        this.table = tableFactory.createTable("table")
    }

}