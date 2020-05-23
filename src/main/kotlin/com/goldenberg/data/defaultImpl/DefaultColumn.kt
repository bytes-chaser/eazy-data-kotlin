package com.goldenberg.data.defaultImpl

import com.goldenberg.data.Cell
import com.goldenberg.data.Column
import com.goldenberg.data.Table

class DefaultColumn internal constructor(
    private var table: Table,
    private var name: String,
    private var isComparable: Boolean = true,
    private var defaultValue: Any? = null
) : Column {

    override fun getDefaultValue(): Any? {
        return defaultValue
    }

    override fun getName(): String {
        return name
    }

    override fun getRowsCells(startIndex: Int, endIndex: Int): List<Cell>? {
        return this.table.getCells(this, startIndex, endIndex)
    }

    override fun getTable(): Table {
        return table
    }

    override fun isComparable(): Boolean {
        return isComparable
    }

    override fun toString(): String {
        return StringBuilder(name).append(" ( ").append(if (isComparable) "comparable" else "non-comparable")
            .append(", ").append("default: ").append(defaultValue).append(" )").toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DefaultColumn

        if (name != other.name) return false
        if (isComparable != other.isComparable) return false
        if (defaultValue != other.defaultValue) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + table.getId().hashCode()
        result = 31 * result + isComparable.hashCode()
        result = 31 * result + (defaultValue?.hashCode() ?: 0)
        return result
    }

    internal fun setDefaultValue(defaultValue: Any?) {
        this.defaultValue = defaultValue
    }

    internal fun setIsComparable(isComparable: Boolean) {
        this.isComparable = isComparable
    }

    internal fun setName(name: String) {
        this.name = name
    }

    internal fun setTable(table: Table) {
        this.table = table
    }


}