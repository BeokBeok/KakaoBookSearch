package com.beok.kakaobooksearch.domain.usecase

import com.beok.kakaobooksearch.domain.model.Book
import com.beok.kakaobooksearch.domain.repository.BookSearchRepository
import javax.inject.Inject

class BookTitleSearchUseCaseImpl @Inject constructor(
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
