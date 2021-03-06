package com.beok.kakaobooksearch.domain.usecase

import com.beok.kakaobooksearch.domain.repository.BookSearchRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class BookTitleSearchUseCaseImplTest {

    private val bookSearchRepository: BookSearchRepository = BookSearchRepository.Fake()
    private val useCase: BookTitleSearchUseCase = BookTitleSearchUseCase.Fake()

    @Test
    fun `책 제목으로 검색합니다`() = runBlocking {
        val bookTitle = "미움 받을 용기"
        val expected = bookSearchRepository.searchBookByTitle(title = bookTitle)
        val actual = useCase.execute(
            param = BookTitleSearchUseCaseImpl.Param(query = bookTitle)
        )

        assertEquals(expected, actual.getOrNull())
    }
}
