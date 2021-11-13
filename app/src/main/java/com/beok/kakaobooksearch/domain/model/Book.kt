package com.beok.kakaobooksearch.domain.model

data class Book(
    override val isEnd: Boolean,
    val document: List<Document>
) : Pageable
