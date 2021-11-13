package com.beok.kakaobooksearch.presenter.main

import com.beok.kakaobooksearch.InstantExecutorExtension
import com.beok.kakaobooksearch.domain.model.Book
import com.beok.kakaobooksearch.domain.model.Document
import com.beok.kakaobooksearch.domain.usecase.BookTitleSearchUseCase
import com.beok.kakaobooksearch.domain.usecase.BookTitleSearchUseCaseImpl
import com.beok.kakaobooksearch.presenter.search.vo.DocumentVO
import io.mockk.coEvery
import io.mockk.mockk
import java.util.Date
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(value = [InstantExecutorExtension::class])
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

    @Test
    fun `책 아이템을 클릭합니다`() {
        val item = DocumentVO(
            isbn = "",
            thumbnail = "",
            title = "",
            authors = "",
            publisher = "",
            datetime = "",
            price = 0,
            salePercent = 0,
            contents = ""
        )
        viewModel.onClickedItem(item = item)

        assertEquals(viewModel.clickedItem.value, item)
    }

    @Test
    fun `즐겨찾기 버튼을 클릭합니다`() {
        `책 리스트 데이터 설정`()

        val item = DocumentVO(
            isbn = "1",
            thumbnail = "",
            title = "",
            authors = "",
            publisher = "",
            datetime = "",
            price = 0,
            salePercent = 0,
            contents = "",
            isLike = false
        )

        viewModel.likeItem(item, true)

        assertEquals(viewModel.document.value?.first()?.isLike, true)
    }

    private fun `책 리스트 데이터 설정`() {
        val bookName = "미움받을 용기"
        val mockResponse = Book(
            isEnd = false,
            document = listOf(
                Document(
                    isbn = "1",
                    thumbnail = "",
                    title = "",
                    authors = listOf(),
                    publisher = "",
                    datetime = Date(0),
                    salePrice = 0,
                    price = 0,
                    contents = ""
                )
            )
        )
        coEvery {
            bookTitleSearchUseCase
                .execute(param = BookTitleSearchUseCaseImpl.Param(query = bookName))
                .getOrNull()
        } returns mockResponse
        viewModel.searchByBookName(bookName = bookName)
    }
}
