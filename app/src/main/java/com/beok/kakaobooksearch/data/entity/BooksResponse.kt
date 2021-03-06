package com.beok.kakaobooksearch.data.entity

import com.beok.kakaobooksearch.data.mapper.DataToDomainMapper
import com.beok.kakaobooksearch.data.mapper.toDto
import com.beok.kakaobooksearch.domain.model.Book
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BooksResponse(

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
