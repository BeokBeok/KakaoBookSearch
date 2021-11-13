package com.beok.kakaobooksearch.main

import com.beok.kakaobooksearch.domain.model.Book
import com.beok.kakaobooksearch.domain.usecase.BookTitleSearchUseCase
import com.beok.kakaobooksearch.domain.usecase.BookTitleSearchUseCaseImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class MainViewModelTest {

    private val bookTitleSearchUseCase: BookTitleSearchUseCase = mockk(relaxed = true)
    private lateinit var viewModel: MainViewModel

    @BeforeEach
    fun setup() {
        viewModel =
            MainViewModel(bookTitleSearchUseCase = bookTitleSearchUseCase)
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
