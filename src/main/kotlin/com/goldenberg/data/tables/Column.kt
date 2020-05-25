package com.goldenberg.data.tables

interface Column {

    fun getDefaultValue(): Any?

    fun getName(): String

    fun getRowsCells(
            startIndex: Int = 0,
            endIndex: Int = if (getTable().getRowSize() == 0) 0 else getTable().getRowSize() - 1
    ): List<Cell> {
        return this.getTable().getCells(this, startIndex, endIndex)
    }

    fun getRowsCellsValues(
            startIndex: Int = 0,
            endIndex: Int = if (getTable().getRowSize() == 0) 0 else getTable().getRowSize() - 1
    ): List<Any?> {
        return this.getTable().getCellsValues(this, startIndex, endIndex)
    }

    fun getTable(): Table

    fun isComparable(): Boolean

}
