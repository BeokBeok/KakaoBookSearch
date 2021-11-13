package com.beok.kakaobooksearch.data.remote

import com.beok.kakaobooksearch.domain.model.Book
import com.beok.kakaobooksearch.domain.repository.BookSearchRepository
import javax.inject.Inject

class BookSearchRepositoryImpl @Inject constructor(
    private val bookSearchAPI: BookSearchAPI
) : BookSearchRepository {

    override suspend fun searchBookByTitle(title: String, page: Int): Book = bookSearchAPI
        .searchBookByTitle(query = title, page = page)
        .toDto()
}
