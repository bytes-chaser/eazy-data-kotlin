package com.goldenberg.data.tables.defaultImpl

import com.goldenberg.data.tables.Column
import com.goldenberg.data.tables.ComparableCell
import com.goldenberg.data.tables.Row
import com.goldenberg.data.tables.Table

class DefaultComparableCell internal constructor(table: Table, row: Row, column: Column, value: Comparable<*>):
    DefaultCell(table, row, column, value), ComparableCell {

}