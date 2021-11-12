package com.beok.kakaobooksearch.search.data.remote

import com.beok.kakaobooksearch.search.domain.model.Book
import com.beok.kakaobooksearch.search.domain.repository.BookSearchRepository
import javax.inject.Inject

internal class BookSearchRepositoryImpl @Inject constructor(
    private val bookSearchAPI: BookSearchAPI
) : BookSearchRepository {

    override suspend fun searchBookByTitle(title: String, page: Int): Book = bookSearchAPI
        .searchBookByTitle(query = title, page = page)
        .toDto()
}
