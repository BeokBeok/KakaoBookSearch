package com.beok.search.data.remote

import com.beok.search.domain.model.Book
import com.beok.search.domain.repository.BookSearchRepository

internal class BookSearchRepositoryImpl(
    private val bookSearchAPI: BookSearchAPI
) : BookSearchRepository {

    override suspend fun searchBookByTitle(title: String, page: Int): Book = bookSearchAPI
        .searchBookByTitle(query = title, page = page)
        .toDto()
}
