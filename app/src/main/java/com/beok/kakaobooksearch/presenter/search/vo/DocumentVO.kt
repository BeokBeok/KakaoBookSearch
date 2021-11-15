package com.beok.kakaobooksearch.presenter.search.vo

import android.os.Parcelable
import com.beok.kakaobooksearch.domain.model.Document
import java.util.Date
import kotlinx.parcelize.Parcelize

@Parcelize
data class DocumentVO(
    val isbn: String,
    val thumbnail: String,
    val title: String,
    val authors: String,
    val publisher: String,
    val datetime: Date,
    val price: Int,
    val salePercent: Int,
    val contents: String,
    val isLike: Boolean = false
) : Parcelable {

    companion object {
        fun fromDomain(model: Document): DocumentVO = DocumentVO(
            isbn = model.isbn,
            thumbnail = model.thumbnail,
            title = model.title,
            authors = model.authors.joinToString(),
            publisher = model.publisher,
            datetime = model.datetime,
            price = if (model.salePrice < 0) model.price else model.salePrice,
            salePercent = model.salePercent(),
            contents = model.contents
        )
    }
}
