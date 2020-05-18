package com.goldenberg.data.defaultImpl

import com.goldenberg.data.*
import java.util.*

open class DefaultTableFactory : TableFactory {

    companion object {
        val instance = DefaultTableFactory()
        private const val CREATE_ROW_REFUSE = "Unable to override row %d in table %s. " +
                "Change index, allow overriding or make sure that table hasn't row with index %d before adding row"
    }

    override fun createTable(id: String): DefaultTable {
        return DefaultTable(id)
    }

    override fun createColumn(table: WritableTable, name: String, isComparable: Boolean, defaultValue: Any?): DefaultColumn {
        val col =  DefaultColumn(table, name, isComparable, defaultValue)
        table.addColumn(col)
        return col
    }

    override fun createColumn(table: WritableTable, col: Column): DefaultColumn {
        return createColumn(table, col.getName(), col.isComparable(), col.getDefaultValue())
    }

    override fun createRow(table: WritableTable, id: Int): DefaultRow {
        val row = DefaultRow(table, id)
        table.addRow(row)
        return row
    }

    override fun createRow(table: WritableTable, row: Row, index: Int, isOverride: Boolean): DefaultRow {
        table.getRowExisted(index)?.let {
            require(isOverride) { String.format(CREATE_ROW_REFUSE, index, table.getId(), index)}
            table.removeRow(index)
        }

        val newRow = createRow(table, index)

        row.getTable().getColumns().stream()
            .map { it.getName() }
            .filter{ Objects.nonNull(table.getColumn(it)) }
            .forEach{ row[it]?.let { cell -> newRow[cell.getColumn()] = cell }}

        return newRow
    }

    override fun createCell(table: WritableTable, row: Row, column: Column, value: Any?): DefaultCell {
        val cell = DefaultCell(table, row, column, value)
        row.add(column, cell)
        return cell
    }

    override fun createCell(table: WritableTable, cell: Cell): DefaultCell {
        var col = cell.getColumn()
        if (!table.isColumnExists(col))
            col = getCellCol(table, cell)

        var row = cell.getRow()
        if (!table.isRowExists(row.getIndex()))
            row = getCellRow(table, cell)

        return createCell(table, row, col, cell.getValue())
    }

    private fun getCellRow(table: WritableTable, cell: Cell): Row {
        val rowIndex = cell.getRow().getIndex()
        return createRow(table, rowIndex)
    }

    private fun getCellCol(table: WritableTable, cell: Cell): Column {
        val cellCol = cell.getColumn()
        val name = cellCol.getName()
        return createColumn(table, name, cellCol.isComparable(), cellCol.getDefaultValue())
    }

}