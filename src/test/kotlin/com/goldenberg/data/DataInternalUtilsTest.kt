package com.goldenberg.data

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DataInternalUtilsTest {

    @Test
    fun `Produce Illegal Indexes State Exception Test`() {
        assertThrows<IllegalArgumentException> { checkIndexesParams(5, 3, 6) }
    }

    @Test
    fun `Produce Illegal Start Index State Exception Test`() {
        assertThrows<IllegalArgumentException> { checkIndexesParams(1, 7, 6) }
    }

    @Test
    fun `Produce Illegal End Index State Exception Test`() {
        assertThrows<IllegalArgumentException> { checkIndexesParams(-1, 4, 6) }
    }
}