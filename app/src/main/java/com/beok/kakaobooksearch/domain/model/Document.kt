package com.beok.kakaobooksearch.domain.model

import java.util.Date

data class Document(
    val isbn: String,
    val thumbnail: String,
    val title: String,
    val authors: List<String>,
    val publisher: String,
    val datetime: Date,
    val salePrice: Int,
    val price: Int,
    val contents: String
) {
    fun salePercent(): Int {
        if (price <= 0 || salePrice <= 0) return 0
        val salePercent = ((1 - (salePrice.toFloat() / price.toFloat())) * 100).toInt()
        return if (salePercent > 0) salePercent else 0
    }
}
