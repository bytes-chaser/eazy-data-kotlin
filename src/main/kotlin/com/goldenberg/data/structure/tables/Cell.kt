package com.goldenberg.data.structure.tables

interface Cell {

    fun getColumn(): Column

    fun getRow(): Row

    fun getValue(): Any?

    fun getTable(): Table

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
}
