package com.goldenberg.data

import java.util.stream.Collectors

interface WritableTable: Table {

    fun addColumn(column: Column)

    fun removeRow(index: Int): Row?

    fun removeColumn(column: Column): Column?

    fun addRow(row: Row)

    fun addColumns(columns: Array<Column>)
    {
        columns.forEach { addColumn(it) }
    }

    fun addColumns(columns: Collection<Column>)
    {
        columns.forEach { addColumn(it) }
    }

    fun removeColumn(column: String): Column?
    {
        return getColumn(column)?.let { removeColumn(it) }
    }

    fun removeRow(row: Row): Row?
    {
        return removeRow(row.getIndex())
    }

    fun removeRows(rows: Collection<Row>): List<Row>
    {
        return rows.stream().map { removeRow(it) }.collect(Collectors.toList()).filterNotNull()
    }

    fun removeRows(startIndex: Int = 0, endIndex: Int = getRowSize() - 1): List<Row>
    {
        DataInternalUtils.checkIndexesParams(startIndex, endIndex, getRowSize())
        return (startIndex..endIndex).mapNotNull { removeRow(it) }.toList()

    }

}