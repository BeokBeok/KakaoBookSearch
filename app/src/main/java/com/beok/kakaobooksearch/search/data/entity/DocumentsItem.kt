package com.beok.kakaobooksearch.search.data.entity

import com.beok.kakaobooksearch.mapper.DataToDomainMapper
import com.beok.kakaobooksearch.search.domain.model.Document
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.Date

@JsonClass(generateAdapter = true)
internal data class DocumentsItem(

	@Json(name="datetime")
	val datetime: Date? = null,

	@Json(name="thumbnail")
	val thumbnail: String? = null,

	@Json(name="translators")
	val translators: List<String?>? = null,

	@Json(name="contents")
	val contents: String? = null,

	@Json(name="price")
	val price: Int? = null,

	@Json(name="isbn")
	val isbn: String? = null,

	@Json(name="publisher")
	val publisher: String? = null,

	@Json(name="title")
	val title: String? = null,

	@Json(name="sale_price")
	val salePrice: Int? = null,

	@Json(name="url")
	val url: String? = null,

	@Json(name="authors")
	val authors: List<String>? = null,

	@Json(name="status")
	val status: String? = null
) : DataToDomainMapper<Document> {

	override fun toDto(): Document = Document(
		isbn = isbn ?: "",
		thumbnail = thumbnail ?: "",
		title = title ?: "",
		authors = authors ?: emptyList(),
		publisher = publisher ?: "",
		datetime = datetime ?: Date(0),
		salePrice = salePrice ?: 0,
		price = price ?: 0,
	)
}
