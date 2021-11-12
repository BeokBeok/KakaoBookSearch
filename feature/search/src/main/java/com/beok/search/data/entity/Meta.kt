package com.beok.search.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class Meta(

	@Json(name="total_count")
	val totalCount: Int? = null,

	@Json(name="is_end")
	val isEnd: Boolean? = null,

	@Json(name="pageable_count")
	val pageableCount: Int? = null
)
