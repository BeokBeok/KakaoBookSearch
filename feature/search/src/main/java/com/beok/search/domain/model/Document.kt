package com.beok.search.domain.model

import java.util.Date

internal data class Document(
    val isbn: String,
    val thumbnail: String,
    val title: String,
    val authors: List<String>,
    val publisher: String,
    val datetime: Date,
    val salePrice: Int,
    val price: Int
) {
    fun salePercent(): Int {
        if (price <= 0 || salePrice <= 0) return 0
        return ((1 - (salePrice.toFloat() / price.toFloat())) * 100).toInt()
    }
}
