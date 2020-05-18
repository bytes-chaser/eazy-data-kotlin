package com.goldenberg.data

import org.junit.jupiter.api.BeforeEach

abstract class AbstractTableInterfacesTest {

    open lateinit var  tableFactory: TableFactory

    @BeforeEach
    open fun setup() {
        this.tableFactory = setTableFactory()
    }

    abstract fun setTableFactory(): TableFactory

}