package com.beok.kakaobooksearch.search.data.entity

import com.beok.kakaobooksearch.mapper.DataToDomainMapper
import com.beok.kakaobooksearch.mapper.toDto
import com.beok.kakaobooksearch.search.domain.model.Book
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class BooksResponse(

	@Json(name="documents")
	val documents: List<DocumentsItem>? = null,

	@Json(name="meta")
	val meta: Meta? = null
) : DataToDomainMapper<Book> {

	override fun toDto(): Book = Book(
		isEnd = meta?.isEnd ?: false,
		document = documents?.toDto() ?: emptyList()
	)
}
