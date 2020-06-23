package com.goldenberg.data.structure.tables.defaultImpl.column


import com.goldenberg.data.structure.tables.Table
import com.goldenberg.data.structure.tables.TableFactory
import com.goldenberg.data.structure.tables.WritableTable
import com.goldenberg.data.structure.tables.column.AbstractTableColumnGeneralTest
import com.goldenberg.data.structure.tables.defaultImpl.DefaultColumn
import com.goldenberg.data.structure.tables.defaultImpl.DefaultTableFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.*

class DefaultTableColumnGeneralTest : AbstractTableColumnGeneralTest() {

    lateinit var table: Table

    @BeforeEach
    override fun setup() {
        super.setup()
        table = this.col.getTable()
        val writableTable = table as WritableTable
        val row0 = tableFactory.createRow(writableTable, 0)
        val row1 = tableFactory.createRow(writableTable, 1)
        val row2 = tableFactory.createRow(writableTable, 2)

        tableFactory.createCell(writableTable, row0, col, "val0")
        tableFactory.createCell(writableTable, row1, col, "val1")
        tableFactory.createCell(writableTable, row2, col, "val2")

    }

    @Test
    override fun `Get Rows Cells`() {
        val rowsCells = col.getRowsCells().map { it.getValue() to it }.toMap()
        assertEquals("val0", rowsCells["val0"]?.getValue())
        assertEquals("val1", rowsCells["val1"]?.getValue())
        assertEquals("val2", rowsCells["val2"]?.getValue())
    }

    @Test
    override fun `Get Rows Cells Custom Start Index`() {
        val rowsCells = col.getRowsCells(startIndex = 1).map { it.getValue() to it }.toMap()
        assertNull(rowsCells["val0"])
        assertEquals("val1", rowsCells["val1"]?.getValue())
        assertEquals("val2", rowsCells["val2"]?.getValue())
    }

    @Test
    override fun `Get Rows Cells Custom Outbound Start Index`() {
        assertThrows<IllegalArgumentException> { col.getRowsCells(startIndex = 6) }
    }

    @Test
    override fun `Get Rows Cells Custom End Index`() {
        val rowsCells = col.getRowsCells(endIndex = 1).map { it.getValue() to it }.toMap()
        assertEquals("val0", rowsCells["val0"]?.getValue())
        assertEquals("val1", rowsCells["val1"]?.getValue())
        assertNull(rowsCells["val2"])
    }

    @Test
    override fun `Get Rows Cells Custom Outbound End Index`() {
        assertThrows<IllegalArgumentException> { col.getRowsCells(endIndex = 6) }
    }

    @Test
    override fun `Get Rows Cells Custom Start and End Indexes`() {
        val rowsCells = col.getRowsCells(startIndex = 1, endIndex = 1).map { it.getValue() to it }.toMap()
        assertNull(rowsCells["val0"])
        assertEquals("val1", rowsCells["val1"]?.getValue())
        assertNull(rowsCells["val2"])
    }

    @Test
    override fun `Get Rows Cells Value`() {
        val rowsCells = col.getRowsCellsValues()
        assertEquals("val0", rowsCells[0])
        assertEquals("val1", rowsCells[1])
        assertEquals("val2", rowsCells[2])
    }

    @Test
    override fun `Get Rows Cells Values Custom Start Index`() {
        val rowsCells = col.getRowsCellsValues(startIndex = 1)
        assertEquals("val1", rowsCells[0])
        assertEquals("val2", rowsCells[1])
        assertThrows<IndexOutOfBoundsException> { rowsCells[2] }

    }

    @Test
    override fun `Get Rows Cells Values Custom Outbound Start Index`() {
        assertThrows<IllegalArgumentException> { col.getRowsCellsValues(startIndex = 6) }
    }

    @Test
    override fun `Get Rows Cells Values Custom End Index`() {
        val rowsCells = col.getRowsCellsValues(endIndex = 1)
        assertEquals("val0", rowsCells[0])
        assertEquals("val1", rowsCells[1])
        assertThrows<IndexOutOfBoundsException> { rowsCells[2] }
    }

    @Test
    override fun `Get Rows Cells Values Custom Outbound End Index`() {
        assertThrows<IllegalArgumentException> { col.getRowsCellsValues(endIndex = 6) }
    }

    @Test
    override fun `Get Rows Cells Values Custom Start and End Indexes`() {
        val rowsCells = col.getRowsCellsValues(startIndex = 1, endIndex = 1)
        assertEquals("val1", rowsCells[0])
        assertThrows<IndexOutOfBoundsException> { rowsCells[1] }
        assertThrows<IndexOutOfBoundsException> { rowsCells[2] }
    }

    @Test
    override fun `Get Column Name`() {
        assertEquals("col1", col.getName())
    }

    @Test
    override fun `Get Column Default`() {
        assertEquals("def", col.getDefaultValue())
    }

    @Test
    override fun `Get Column Comparable True`() {
       assertTrue { col.isComparable() }
    }

    @Test
    override fun `Get Column Table `() {
        assertEquals(table, col.getTable())
    }

    @Test
    override fun `Print toString() Test`() {
        assertEquals("col1 ( comparable, default: def )", this.col.toString())

    }

    @Test
    override fun `Print toString() Test Non-Comparable`() {
        val defaultColumn = this.col as DefaultColumn
        defaultColumn.setIsComparable(false)
        assertEquals("col1 ( non-comparable, default: def )", defaultColumn.toString())
    }

    @Test
    override fun `Print toString() Test Null Default`() {
        val defaultColumn = this.col as DefaultColumn
        defaultColumn.setDefaultValue(null)
        assertEquals("col1 ( comparable, default: null )", defaultColumn.toString())
    }

    @Test
    override fun `Compare Column HashCode All Same`() {
        val col = tableFactory.createColumn(tableFactory.createTable("name"),"col1", true, "def")
        assertEquals(this.col.hashCode(), col.hashCode())
    }

    @Test
    override fun `Compare Column HashCode Different Table`() {
        val col = tableFactory.createColumn(tableFactory.createTable("name1"),"col1", true, "def")
        assertNotEquals(this.col.hashCode(), col.hashCode())
    }

    @Test
    override fun `Compare Column HashCode Different Name`() {
        val col = tableFactory.createColumn(tableFactory.createTable("name"),"col2", true, "def")
        assertNotEquals(this.col.hashCode(), col.hashCode())
    }

    @Test
    override fun `Compare Column HashCode Different Comparable`() {
        val col = tableFactory.createColumn(tableFactory.createTable("name"),"col1", false, "def")
        assertNotEquals(this.col.hashCode(), col.hashCode())    }

    @Test
    override fun `Compare Column HashCode Different Default Value`() {
        val col = tableFactory.createColumn(tableFactory.createTable("name"),"col1", true, "def2")
        assertNotEquals(this.col.hashCode(), col.hashCode())
    }

    @Test
    override fun `Compare Column HashCode All Different`() {
        val col = tableFactory.createColumn(tableFactory.createTable("name1"),"col2", false, "def2")
        assertNotEquals(this.col.hashCode(), col.hashCode())
    }

    @Test
    fun `Set Table Test`() {
        val defaultColumn = this.col as DefaultColumn
        assertEquals("name", this.col.getTable().getId())

        defaultColumn.setTable(tableFactory.createTable("name2"))
        assertEquals("name2", this.col.getTable().getId())
    }

    @Test
    fun `Set Name Test`() {
        val defaultColumn = this.col as DefaultColumn
        assertEquals("col1", this.col.getName())

        defaultColumn.setName("col2")
        assertEquals("col2", this.col.getName())
    }

    @Test
    fun `Set IsComparable Test`() {
        val defaultColumn = this.col as DefaultColumn
        assertTrue { this.col.isComparable() }

        defaultColumn.setIsComparable(false)
        assertFalse { this.col.isComparable() }

    }

    @Test
    fun `Set Default Value Test`() {
        val defaultColumn = this.col as DefaultColumn
        assertEquals("def", this.col.getDefaultValue())

        defaultColumn.setDefaultValue("def2")
        assertEquals("def2", this.col.getDefaultValue())
    }

    override fun setTableFactory(): TableFactory {
        return DefaultTableFactory.instance
    }
}
