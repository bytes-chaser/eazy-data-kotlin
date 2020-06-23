package com.goldenberg.data.structure.tables.defaultImpl

import com.goldenberg.data.structure.tables.Row
import com.goldenberg.data.structure.tables.RowFilter
import com.goldenberg.data.structure.tables.removeFilterPredicate

class DefaultRowFilter: RowFilter {

    private var rowFilters: MutableCollection<(Row) -> Boolean> = mutableListOf()

    override fun addRowPredicate(predicate:  (Row) -> Boolean): Boolean {
        return this.rowFilters.add(predicate)
    }

    override fun addRowPredicate(row: Row): Boolean {
        return addRowPredicate {it == row}
    }

    override fun removeRowPredicate(predicate: (Row) -> Boolean): Boolean {
        return this.rowFilters.remove(predicate)
    }

    override fun removeRowPredicate(row: Row): Boolean {
        return removeFilterPredicate(this.rowFilters, row)
    }

    override fun isRowPredicatesExists(): Boolean {
        return !this.rowFilters.isEmpty()
    }

    override fun getRowPredicatesArray(): Array<(Row) -> Boolean> {
        return this.rowFilters.toTypedArray()
    }

    override fun getRowPredicatesCollection(): Collection<(Row) -> Boolean> {
        return this.rowFilters
    }


}
