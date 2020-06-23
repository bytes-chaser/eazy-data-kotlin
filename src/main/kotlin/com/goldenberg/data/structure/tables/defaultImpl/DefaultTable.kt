package com.goldenberg.data.structure.tables.defaultImpl

import com.goldenberg.data.structure.tables.Column
import com.goldenberg.data.structure.tables.Row
import com.goldenberg.data.structure.tables.WritableTable
import java.util.stream.Collectors

class DefaultTable internal constructor(private var id: String) : WritableTable {

    companion object {
        const val ROW = "row"
        const val COLUMN = "column"
        const val ILLEGAL_OBJECT_MESSAGE: String =
            "Operation restriction. This %s assigned for table %s instead of table %s."
    }

    override var size: Int = 0

    private var columns: MutableMap<String, Column> = mutableMapOf()
    private var rows: MutableMap<Int, Row> = sortedMapOf()


    override fun addColumn(column: Column)
    {
        val table = column.getTable()
        require(table == this) { String.format(ILLEGAL_OBJECT_MESSAGE, COLUMN, table.getId(), getId()) }
        columns[column.getName()] = column
    }

    override fun addRow(row: Row)
    {
        val table = row.getTable()
        require(table == this) { String.format(ILLEGAL_OBJECT_MESSAGE, ROW, table.getId(), getId()) }
        rows[row.getIndex()] = row
        size = rows.size

    }

    override fun contains(element: Row): Boolean
    {
        return this.rows.containsValue(element)
    }

    override fun containsAll(elements: Collection<Row>): Boolean {
        return this.rows.values.containsAll(elements)
    }

    override fun getColumn(column: String): Column?
    {
        return this.columns[column]
    }

    override fun getColumns(): Collection<Column>
    {
        return columns.values
    }

    override fun getColumnSize(): Int
    {
        return columns.size
    }

    override fun getId(): String
    {
        return id
    }

    override fun getRow(index: Int): Row
    {
        return getRowExisted(index) ?: DefaultRow(this, index)
    }

    override fun getRowExisted(index: Int): Row?
    {
        return rows[index]
    }

    override fun getRows(): Collection<Row>
    {
        return rows.values
    }

    override fun getRowSize(): Int
    {
        return rows.size
    }

    override fun removeColumn(column: Column): Column?
    {
        removeColumns(column)
        return this.columns.remove(column.getName())
    }

    private fun removeColumns(column: Column)
    {
        for (row in this.rows.values) row.remove(column)
    }

    override fun removeRow(index: Int): Row?
    {
        val row = rows.remove(index)
        size = rows.size
        return row
    }

    override fun toString(): String {

        var header = columns.values.stream().map { it.toString() }.collect(Collectors.joining(", ")).plus("\n")
        val length = header.length
        for (i in 0 until length) header = header.plus("=")
        header = header.plus("\n")
        return header.plus(rows.values.stream().map { it.toString() }.collect(Collectors.joining("\n")))
    }

    override fun equals(other: Any?): Boolean
    {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DefaultTable

        if (id != other.id) return false
        if (columns != other.columns) return false
        if (rows != other.rows) return false

        return true
    }

    override fun hashCode(): Int
    {
        var result = id.hashCode()
        result = 31 * result + columns.hashCode()
        result = 31 * result + rows.hashCode()
        return result
    }

    override fun iterator(): MutableIterator<Row>
    {
        return rows.values.iterator()
    }

    internal fun setId(id: String)
    {
        this.id = id
    }
}
