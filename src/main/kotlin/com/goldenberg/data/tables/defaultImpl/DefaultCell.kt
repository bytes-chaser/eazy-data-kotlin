package com.goldenberg.data.tables.defaultImpl

import com.goldenberg.data.tables.Cell
import com.goldenberg.data.tables.Column
import com.goldenberg.data.tables.Row
import com.goldenberg.data.tables.Table

open class DefaultCell internal constructor(
        private var table: Table,
        private var row: Int,
        private var column: String,
        private var value: Any?
) : Cell {

    internal constructor(table: Table, row: Row, column: Column, value: Any?)
            : this(table, row.getIndex(), column.getName(), value)

    override fun getColumn(): Column {
        return table.getColumn(column)!!
    }

    override fun getRow(): Row {
        return table.getRow(row)
    }

    override fun getValue(): Any? {
        return value
    }

    override fun getTable(): Table {
        return this.table
    }

    override fun toString(): String {
        return value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if(value == other) return true

        other as DefaultCell

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        var result = table.getId().hashCode()
        result = 31 * result + row.hashCode()
        result = 31 * result + column.hashCode()
        result = 31 * result + (value?.hashCode() ?: 0)
        return result
    }

    internal fun setValue(value: Any?) {
        this.value = value
    }


}
