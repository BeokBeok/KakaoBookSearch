package com.beok.kakaobooksearch.search.vo

import com.beok.kakaobooksearch.util.DateConverter
import com.beok.kakaobooksearch.domain.model.Document

data class DocumentVO(
    val isbn: String,
    val thumbnail: String,
    val title: String,
    val authors: String,
    val publisher: String,
    val datetime: String,
    val price: Int,
    val salePercent: Int,
    val contents: String,
    val isLike: Boolean = false
) {
    companion object {
        fun fromDomain(model: Document): DocumentVO = DocumentVO(
            isbn = model.isbn,
            thumbnail = model.thumbnail,
            title = model.title,
            authors = model.authors.joinToString(),
            publisher = model.publisher,
            datetime = DateConverter.toYYYYMM(date = model.datetime),
            price = if (model.salePrice < 0) model.price else model.salePrice,
            salePercent = model.salePercent(),
            contents = model.contents
        )
    }
}
