package com.goldenberg.data

interface RowFilter {

    fun addRowPredicate(predicate:  (Row) -> Boolean): Boolean

    fun addRowPredicate(row: Row): Boolean

    fun removeRowPredicate(predicate: (Row) -> Boolean): Boolean

    fun removeRowPredicate(row: Row): Boolean

    fun isRowPredicatesExists(): Boolean

    fun getRowPredicatesArray(): Array<(Row) -> Boolean>

    fun getRowPredicatesCollection(): Collection<(Row) -> Boolean>
}