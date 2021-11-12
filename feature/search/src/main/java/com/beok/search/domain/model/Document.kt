package com.beok.search.domain.model

import java.util.Date

internal data class Document(
    val isbn: String,
    val thumbnail: String,
    val title: String,
    val contents: String,
    val price: Int,
    val datetime: Date
)
