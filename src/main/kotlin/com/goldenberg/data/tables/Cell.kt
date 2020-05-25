package com.goldenberg.data.tables

interface Cell: Comparable<Cell> {

    fun getColumn(): Column

    fun getRow(): Row

    fun getValue(): Any?

    fun hasValue(): Boolean {
        var isHasValue = false
        getValue()?.let {
            isHasValue = when (it) {
                is String -> it.isNotEmpty()
                else -> it.toString().isNotEmpty()
            }
        }
        return isHasValue
    }

    fun isDefault(): Boolean {
        return getColumn().getDefaultValue() == getValue()
    }

    fun getTable(): Table

    override fun compareTo(other: Cell): Int {
        val otherValue = other.getValue()
        val value = getValue()
        if (value is Comparable<*> && otherValue is Comparable<*>)
            return compareValues(value, otherValue)
        return -1
    }
}
