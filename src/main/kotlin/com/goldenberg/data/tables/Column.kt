package com.goldenberg.data.tables

interface Column {

    fun getDefaultValue(): Any?

    fun getName(): String

    fun getRowsCells(
        startIndex: Int = 0,
        endIndex: Int = if (getTable().getRowSize() == 0) 0 else getTable().getRowSize() - 1
    ): List<Cell>?

    fun getTable(): Table

    fun isComparable(): Boolean

}
