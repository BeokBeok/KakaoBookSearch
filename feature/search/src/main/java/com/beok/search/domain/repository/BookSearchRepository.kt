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
