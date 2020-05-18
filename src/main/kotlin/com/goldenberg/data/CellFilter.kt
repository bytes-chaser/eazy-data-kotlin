package com.goldenberg.data

interface CellFilter: RowFilter {

    fun addCellPredicate(predicate:  (Cell) -> Boolean): Boolean

    fun addCellPredicate(cell: Cell): Boolean

    fun removeCellPredicate(predicate: (Cell) -> Boolean): Boolean

    fun removeCellPredicate(cell: Cell): Boolean

    fun isCellPredicateExists(): Boolean

    fun getCellPredicatesArray(): Array<(Cell) -> Boolean>

    fun getCellPredicatesCollection(): Collection<(Cell) -> Boolean>
}