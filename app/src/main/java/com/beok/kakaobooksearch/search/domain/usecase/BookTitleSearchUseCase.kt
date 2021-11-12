package com.beok.kakaobooksearch.search.domain.usecase

import com.beok.kakaobooksearch.search.domain.model.Book
import com.beok.kakaobooksearch.search.domain.model.Document
import java.util.Date

internal interface BookTitleSearchUseCase {

    suspend fun execute(param: BookTitleSearchUseCaseImpl.Param): Result<Book>

    class Fake : BookTitleSearchUseCase {

        override suspend fun execute(
            param: BookTitleSearchUseCaseImpl.Param
        ): Result<Book> = runCatching {
            Book(
                isEnd = false,
                document = listOf(
                    Document(
                        isbn = "식별자",
                        thumbnail = "썸네일 URL",
                        title = "책 이름",
                        authors = listOf("강현석", "노현석"),
                        publisher = "출판사",
                        datetime = Date(1636642800), // 2021-11-12 00:00:00,
                        salePrice = 13410,
                        price = 14900
                    )
                )
            )
        }
    }
}
