package com.goldenberg.data

interface Cell: Comparable<Cell> {

    fun getColumn(): Column

    fun getRow(): Row

    fun getValue(): Any?

    fun isDefault(): Boolean
    {
        return getColumn().getDefaultValue() == getValue()
    }

    fun getTable(): Table

    override fun compareTo(other: Cell): Int
    {
        val otherValue = other.getValue()
        val value = getValue()
        if (value is Comparable<*> && otherValue is Comparable<*>)
            return compareValues(value, otherValue)
        return -1
    }
}