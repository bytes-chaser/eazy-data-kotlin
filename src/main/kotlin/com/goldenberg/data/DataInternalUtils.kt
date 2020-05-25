package com.goldenberg.data


private const val ILLEGAL_INDEXES_STATE: String = "endIndex suppose to be less then startIndex"
private const val ILLEGAL_END_INDEX_STATE: String = "endIndex suppose to be less then rows count"
private const val ILLEGAL_START_INDEX_STATE: String = "startIndex suppose to be bigger then -1 then rows count"

fun checkIndexesParams(startIndex: Int, endIndex: Int, rowSize: Int) {
    require(startIndex <= endIndex) { ILLEGAL_INDEXES_STATE }
    require(endIndex < rowSize) { ILLEGAL_END_INDEX_STATE }
    require(startIndex > -1) { ILLEGAL_START_INDEX_STATE }
}