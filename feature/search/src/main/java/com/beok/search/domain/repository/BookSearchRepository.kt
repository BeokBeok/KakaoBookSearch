package com.beok.search.domain.repository

import com.beok.search.domain.model.Book
import com.beok.search.domain.model.Document
import java.util.Date

internal interface BookSearchRepository {

    suspend fun searchBookByTitle(title: String, page: Int = 1): Book

    class Fake : BookSearchRepository {

        override suspend fun searchBookByTitle(title: String, page: Int): Book = Book(
            isEnd = false,
            document = listOf(
                Document(
                    isbn = "식별자",
                    datetime = Date(1636642800), // 2021-11-12 00:00:00,
                    thumbnail = "썸네일 URL",
                    contents = "컨텐츠",
                    price = 14900,
                    title = "미움받을 용기",
                )
            )
        )
    }
}
