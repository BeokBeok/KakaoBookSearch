package com.beok.search.domain.model

internal data class Book(
    override val isEnd: Boolean,
    val document: List<Document>
) : Pageable
