package com.beok.search.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class BooksResponse(

	@Json(name="documents")
	val documents: List<DocumentsItem?>? = null,

	@Json(name="meta")
	val meta: Meta? = null
)
