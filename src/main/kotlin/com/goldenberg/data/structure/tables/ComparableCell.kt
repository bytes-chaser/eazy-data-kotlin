package com.goldenberg.data.structure.tables

import com.goldenberg.data.sorting.Sortable

interface ComparableCell: Cell, Sortable<ComparableCell> {

    override fun getSortableValue(): Comparable<*> {
        return getValue() as Comparable<*>
    }

    override fun compareTo(other: ComparableCell): Int {
        val otherValue = other.getSortableValue()
        val value = getSortableValue()
        return compareValues(value, otherValue)
    }
}
