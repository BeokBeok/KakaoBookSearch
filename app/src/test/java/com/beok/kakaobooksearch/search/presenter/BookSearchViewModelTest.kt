package com.beok.kakaobooksearch.search.presenter

import com.beok.kakaobooksearch.InstantExecutorExtension
import com.beok.kakaobooksearch.search.domain.model.Book
import com.beok.kakaobooksearch.search.domain.usecase.BookTitleSearchUseCase
import com.beok.kakaobooksearch.search.domain.usecase.BookTitleSearchUseCaseImpl
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(value = [InstantExecutorExtension::class])
internal class BookSearchViewModelTest {

    private val bookTitleSearchUseCase: BookTitleSearchUseCase = mockk(relaxed = true)
    private lateinit var viewModel: BookSearchViewModel

    @BeforeEach
    fun setup() {
        viewModel =
            BookSearchViewModel(bookTitleSearchUseCase = bookTitleSearchUseCase)
    }

    @Test
    fun `책 이름으로 검색한 리스트를 불러옵니다`() = runBlocking {
        val bookName = "미움받을 용기"
        val mockResponse: Book = mockk(relaxed = true)
        coEvery {
            bookTitleSearchUseCase
                .execute(param = BookTitleSearchUseCaseImpl.Param(query = bookName))
                .getOrNull()
        } returns mockResponse

        viewModel.searchByBookName(bookName = bookName)

        assertEquals(viewModel.document.value, mockResponse.document)
    }
}
