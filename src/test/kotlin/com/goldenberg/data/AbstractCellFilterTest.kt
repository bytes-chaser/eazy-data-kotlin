package com.goldenberg.data

abstract class AbstractCellFilterTest : AbstractRowFilterTest() {

    abstract fun `Add Cell Predicate Test`()

    abstract fun `Add Cell Predicate By Row Test`()

    abstract fun `Remove Cell Predicate Test`()

    abstract fun `Remove Cell Predicate By Cell Test`()

    abstract fun `Is Cell Predicate Exists Test`()

    abstract fun `Get Cell Predicates Array Test`()

    abstract fun `Get Cell Predicates Collection Test`()
}