package com.goldenberg.data.structure.tables.defaultImpl

import com.goldenberg.data.structure.tables.Cell
import com.goldenberg.data.structure.tables.CellFilter
import com.goldenberg.data.structure.tables.Row
import com.goldenberg.data.structure.tables.removeFilterPredicate

class DefaultCellFilter: CellFilter {

    private var rowFilter: DefaultRowFilter
    private var cellFilters: MutableCollection<(Cell) -> Boolean> = mutableListOf()

    constructor() {
        this.rowFilter = DefaultRowFilter()
    }

    constructor(rowFilter: DefaultRowFilter) {
        this.rowFilter = rowFilter
    }

    override fun addCellPredicate(predicate:  (Cell) -> Boolean): Boolean {
        return this.cellFilters.add(predicate)
    }

    override fun addCellPredicate(cell: Cell): Boolean {
        return addCellPredicate {it == cell}
    }

    override fun removeCellPredicate(predicate: (Cell) -> Boolean): Boolean {
        return this.cellFilters.remove(predicate)
    }

    override fun removeCellPredicate(cell: Cell): Boolean {
        return removeFilterPredicate(this.cellFilters, cell)
    }

    override fun isCellPredicateExists(): Boolean {
        return !this.cellFilters.isEmpty()
    }

    override fun getCellPredicatesArray(): Array<(Cell) -> Boolean> {
        return this.cellFilters.toTypedArray()
    }

    override fun getCellPredicatesCollection(): Collection<(Cell) -> Boolean> {
       return this.cellFilters
    }

    override fun addRowPredicate(predicate: (Row) -> Boolean): Boolean {
        return this.rowFilter.addRowPredicate(predicate)
    }

    override fun addRowPredicate(row: Row): Boolean {
        return this.rowFilter.addRowPredicate(row)
    }

    override fun removeRowPredicate(predicate: (Row) -> Boolean): Boolean {
        return this.rowFilter.removeRowPredicate(predicate)
    }

    override fun removeRowPredicate(row: Row): Boolean {
        return this.rowFilter.removeRowPredicate(row)
    }

    override fun isRowPredicatesExists(): Boolean {
        return this.rowFilter.isRowPredicatesExists()
    }

    override fun getRowPredicatesArray(): Array<(Row) -> Boolean> {
        return this.rowFilter.getRowPredicatesArray()
    }

    override fun getRowPredicatesCollection(): Collection<(Row) -> Boolean> {
        return this.rowFilter.getRowPredicatesCollection()
    }


}
