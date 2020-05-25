package com.goldenberg.data.tables.defaultImpl

import com.goldenberg.data.tables.*


open class DefaultTableFactoryTest: AbstractTableFactoryTest() {
    override fun setFactory(): TableFactory {
        return DefaultTableFactory.instance
    }

    override fun setTbl(id: String): WritableTable {
        return DefaultTable(id)
    }

    override fun setCol(name: String, table: WritableTable, isComparable: Boolean, defaultValue: Any?): Column {
        val col = DefaultColumn(table, name, isComparable, defaultValue)
        table.addColumn(col)
        return col
    }

    override fun setRow(id: Int, table: WritableTable): Row {
        val row =  DefaultRow(table, id)
        table.addRow(row)
        return row
    }

    override fun setCell(value: Any, table: WritableTable, row: Row, column: Column): Cell {
        val cell =  DefaultCell(table, row, column, value)
        row.add(column, cell)
        return cell
    }

}
