package com.beok.search.presenter.vo

import com.beok.common.util.DateConverter
import com.beok.search.domain.model.Document

internal data class DocumentVO(
    val isbn: String,
    val thumbnail: String,
    val title: String,
    val authors: String,
    val publisher: String,
    val datetime: String,
    val price: Int,
    val salePercent: Int
) {
    companion object {
        fun fromDomain(model: Document): DocumentVO = DocumentVO(
            isbn = model.isbn,
            thumbnail = model.thumbnail,
            title = model.title,
            authors = model.authors.joinToString(),
            publisher = model.publisher,
            datetime = DateConverter.toYYYYMM(date = model.datetime),
            price = model.salePrice,
            salePercent = model.salePercent()
        )
    }
}
