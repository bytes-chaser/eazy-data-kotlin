package com.goldenberg.data

interface Row : Collection<Cell> {

    fun add(column: Column, value: Cell)

    fun getCell(column: Column): Cell?

    fun getCell(column: String): Cell?
    {
        return getTableColumn(column)?.let { getCell(it) }
    }

    fun getIndex(): Int

    fun getMap(): MutableMap<String, Cell>

    fun getTable(): Table

    fun remove(column: Column): Cell?

    fun remove(column: String): Cell?
    {
        return getTableColumn(column)?.let { remove(it) }
    }

    private fun getTableColumn(column: String): Column?
    {
        return getTable().getColumn(column)
    }


    operator fun get(column: Column): Cell?
    {
        return getCell(column)
    }

    operator fun get(column: String): Cell?
    {
        return getCell(column)
    }

    operator fun set(column: Column, value: Cell)
    {
        add(column, value)
    }

}
