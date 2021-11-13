package com.beok.kakaobooksearch.data.remote

import com.beok.kakaobooksearch.domain.repository.BookSearchRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class BookSearchRepositoryImplTest {

    private val bookSearchAPI: BookSearchAPI = BookSearchAPI.Fake()
    private val repository: BookSearchRepository = BookSearchRepository.Fake()

    @Test
    fun `책 제목으로 검색한 데이터를 불러옵니다`() = runBlocking {
        val query = "미움받을 용기"
        val expected = bookSearchAPI.searchBookByTitle(query = query)
        val actual = repository.searchBookByTitle(title = query)

        assertEquals(expected.toDto(), actual)
    }
}
