package com.beok.search.data.remote

import com.beok.search.data.entity.BooksResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface BookSearchAPI {

    @GET("v3/search/book")
    suspend fun searchBookByTitle(
        @Query("query") query: String,
        @Query("sort") sort: String? = null,
        @Query("page") page: Int = START_PAGE,
        @Query("size") size: Int = DEFAULT_PAGE_SIZE,
        @Query("target") target: String = DEFAULT_TARGET
    ) : BooksResponse

    companion object {
        private const val START_PAGE = 1
        private const val DEFAULT_PAGE_SIZE = 50
        private const val DEFAULT_TARGET = "title"
    }
}
