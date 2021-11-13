package com.beok.kakaobooksearch.domain.usecase

import com.beok.kakaobooksearch.domain.model.Book
import com.beok.kakaobooksearch.domain.model.Document
import java.util.Date

interface BookTitleSearchUseCase {

    suspend fun execute(param: BookTitleSearchUseCaseImpl.Param): Result<Book>

    class Fake : BookTitleSearchUseCase {

        override suspend fun execute(
            param: BookTitleSearchUseCaseImpl.Param
        ): Result<Book> = runCatching {
            Book(
                isEnd = false,
                document = listOf(
                    Document(
                        isbn = "8996991341 9788996991342",
                        thumbnail = "썸네일 URL",
                        title = "미움받을 용기",
                        authors = listOf("기시미 이치로", "고가 후미타케"),
                        publisher = "인플루엔셜",
                        datetime = Date(1636642800), // 2021-11-12 00:00:00,
                        salePrice = 13410,
                        price = 14900,
                        contents = "책 내용"
                    )
                )
            )
        }
    }
}
