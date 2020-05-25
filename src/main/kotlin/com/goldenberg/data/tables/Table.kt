package com.goldenberg.data.tables

import java.util.stream.Collectors
import java.util.stream.Stream

interface Table: Collection<Row> {

    fun getCells(column: Column, startIndex: Int = 0, endIndex: Int = getRowSize() - 1): List<Cell>
    {
        checkIndexesParams(startIndex, endIndex, getRowSize())

        return if (isColumnExists(column.getName())) (startIndex..endIndex)
            .map { getRow(it) }
            .mapNotNull { it.getCell(column) }
            .toList()
        else
            listOf()
    }

    fun getCells(column: String, startIndex: Int = 0, endIndex: Int = getRowSize() - 1): List<Cell>
    {
        return getColumn(column)?.let { getCells(it, startIndex, endIndex) } ?: listOf()
    }

    fun getCells(filter: CellFilter): List<Cell>
    {
        return filter(filter.getCellPredicatesCollection(), getRows(filter).stream().flatMap { it.stream() })
            .collect(Collectors.toList())
    }

    fun getColumn(column: String): Column?

    fun getColumns(): Collection<Column>

    fun getColumnSize(): Int

    fun getCellsValues(column: Column, startIndex: Int = 0, endIndex: Int = getRowSize() - 1): List<Any?>
    {
        return getCells(column, startIndex, endIndex).stream().map { it.getValue() }.collect(Collectors.toList())
    }

    fun getCellsValues(column: String, startIndex: Int = 0, endIndex: Int = getRowSize() - 1): List<Any?>
    {
        return getColumn(column)?.let { getCellsValues(it, startIndex, endIndex) } ?: listOf()
    }

    fun getId(): String

    fun getRow(index: Int): Row

    fun getRowExisted(index: Int): Row?

    fun getRows(): Collection<Row>

    fun getRows(startIndex: Int = 0, endIndex: Int = getRowSize() - 1): Collection<Row>
    {
        checkIndexesParams(startIndex, endIndex, getRowSize())
        return (startIndex..endIndex).map { this.getRow(it) }.toList()
    }

    fun getRows(filter: RowFilter): List<Row>
    {
        return filter(filter.getRowPredicatesCollection(), getRows().stream()).collect(Collectors.toList())
    }

    fun getRowSize(): Int

    fun isColumnExists(name: String): Boolean
    {
        return getColumn(name) != null
    }

    fun isColumnExists(column: Column): Boolean
    {
        return isColumnExists(column.getName())
    }

    override fun isEmpty(): Boolean {
        return size == 0
    }

    fun isRowExists(id: Int): Boolean
    {
        return !getRow(id).isEmpty()
    }

    private fun <T> filter(collection: Collection<(T) -> Boolean>, stream: Stream<T>): Stream<T>
    {
        val iterator = collection.iterator()
        var newStream = stream

        while (iterator.hasNext())
            newStream = filter(iterator.next(), newStream)
        return newStream
    }

    private fun <T> filter(predicate: (T) -> Boolean, stream: Stream<T>): Stream<T>
    {
        return stream.filter(predicate)
    }

}
