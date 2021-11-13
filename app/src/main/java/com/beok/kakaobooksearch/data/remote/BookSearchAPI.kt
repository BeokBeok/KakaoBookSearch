package com.beok.kakaobooksearch.data.remote

import java.util.Date
import retrofit2.http.GET
import retrofit2.http.Query

interface BookSearchAPI {

    @GET("v3/search/book")
    suspend fun searchBookByTitle(
        @Query("query") query: String,
        @Query("sort") sort: String? = null,
        @Query("page") page: Int = START_PAGE,
        @Query("size") size: Int = DEFAULT_PAGE_SIZE,
        @Query("target") target: String = DEFAULT_TARGET
    ): com.beok.kakaobooksearch.data.entity.BooksResponse

    class Fake : BookSearchAPI {

        override suspend fun searchBookByTitle(
            query: String,
            sort: String?,
            page: Int,
            size: Int,
            target: String
        ): com.beok.kakaobooksearch.data.entity.BooksResponse =
            com.beok.kakaobooksearch.data.entity.BooksResponse(
                documents = listOf(
                    com.beok.kakaobooksearch.data.entity.DocumentsItem(
                        datetime = Date(1636642800), // 2021-11-12 00:00:00,
                        thumbnail = "썸네일 URL",
                        translators = listOf("전경아"),
                        contents = "책 내용",
                        price = 14900,
                        isbn = "8996991341 9788996991342",
                        publisher = "인플루엔셜",
                        title = "미움받을 용기",
                        salePrice = 13410,
                        url = "책정보 URL",
                        authors = listOf("기시미 이치로", "고가 후미타케"),
                        status = "정상판매"
                    )
                ),
                meta = com.beok.kakaobooksearch.data.entity.Meta(
                    totalCount = 15,
                    isEnd = false,
                    pageableCount = 13
                )
            )
    }

    companion object {
        private const val START_PAGE = 1
        private const val DEFAULT_PAGE_SIZE = 50
        private const val DEFAULT_TARGET = "title"
    }
}
