package com.beok.search.domain.model

import java.util.Date

internal data class Document(
    val thumbnail: String,
    val title: String,
    val content: String,
    val price: Int,
    val dateTime: Date
)
