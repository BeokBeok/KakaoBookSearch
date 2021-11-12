package com.beok.search.domain.usecase

import com.beok.search.domain.model.Book
import com.beok.search.domain.repository.BookSearchRepository
import javax.inject.Inject

internal class BookTitleSearchUseCaseImpl @Inject constructor(
    private val bookSearchRepository: BookSearchRepository
) : BookTitleSearchUseCase {

    override suspend fun execute(param: Param): Result<Book> = runCatching {
        bookSearchRepository.searchBookByTitle(title = param.query, page = param.page)
    }

    data class Param(
        val query: String,
        val page: Int = 1
    )
}
