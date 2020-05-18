package com.goldenberg.data.defaultImpl


import com.goldenberg.data.Cell
import com.goldenberg.data.Column
import com.goldenberg.data.Row
import com.goldenberg.data.Table
import java.util.stream.Collectors

class DefaultRow internal constructor(private var table: Table, var id: Int) : Row {


    companion object {
        const val COLUMN_DOESNT_EXISTS = "Column '%s' doesn't exists in the table column list"
    }

    override var size: Int = 0

    private var map: MutableMap<String, Cell> = mutableMapOf()

    override fun add(column: Column, value: Cell)
    {
        require(getTable().isColumnExists(column.getName())) { String.format(COLUMN_DOESNT_EXISTS, column.getName())}
        map[column.getName()] = value
        size = map.size
    }

    override fun contains(element: Cell): Boolean
    {
        return map.containsValue(element)
    }

    override fun containsAll(elements: Collection<Cell>): Boolean
    {
        return map.values.containsAll(elements)
    }

    override fun getCell(column: Column): Cell?
    {
        val name = column.getName()
        val existed = table.getColumn(name)
        return existed?.let {
            map[name] ?: DefaultCell(getTable(), this, column, column.getDefaultValue())
        }
    }

    override fun remove(column: Column): Cell?
    {
        val cell = map.remove(column.getName())
        size = map.size
        return cell
    }

    override fun getIndex(): Int
    {
        return id
    }

    override fun getMap(): MutableMap<String, Cell>
    {
        return map
    }


    override fun getTable(): Table
    {
        return table
    }

    override fun isEmpty(): Boolean
    {
        return map.isEmpty()
    }

    override fun iterator(): MutableIterator<Cell>
    {
        return map.values.iterator()
    }

    override fun toString(): String
    {
        return map.entries.stream().map { it.value.toString() }.collect(Collectors.joining(", "))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DefaultRow

        if (id != other.id) return false
        if (map != other.map) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + table.getId().hashCode()
        result = 31 * result + map.hashCode()
        return result
    }

    internal fun setIndex(id: Int) {
        this.id = id
    }

    internal fun setTable(table: Table) {
        this.table = table
    }


}