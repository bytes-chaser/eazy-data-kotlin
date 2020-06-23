package com.goldenberg.data.structure.tables.defaultImpl

import com.goldenberg.data.structure.tables.Column
import com.goldenberg.data.structure.tables.ComparableCell
import com.goldenberg.data.structure.tables.Row
import com.goldenberg.data.structure.tables.Table

class DefaultComparableCell internal constructor(table: Table, row: Row, column: Column, value: Comparable<*>):
        DefaultCell(table, row, column, value), ComparableCell
