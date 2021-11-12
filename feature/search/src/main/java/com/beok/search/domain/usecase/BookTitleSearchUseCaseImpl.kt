package com.beok.search.domain.usecase

import com.beok.search.domain.model.Book
import com.beok.search.domain.repository.BookSearchRepository

internal class BookTitleSearchUseCaseImpl(
    private val bookSearchRepository: BookSearchRepository
) : BookTitleSearchUseCase<BookTitleSearchUseCaseImpl.Param> {

    override suspend fun execute(param: Param): Result<Book> = runCatching {
        bookSearchRepository.searchBookByTitle(title = param.query, page = param.page)
    }

    data class Param(
        val query: String,
        val page: Int = 1
    )
}
