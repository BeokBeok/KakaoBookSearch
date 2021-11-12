package com.beok.search.remote

import com.beok.search.entity.BooksResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.File
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

internal class BookSearchServiceTest {

    private val mockServer = MockWebServer()
    private lateinit var moshi: Moshi
    private lateinit var service: BookSearchService

    @BeforeEach
    fun setup() {
        moshi = Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        service = Retrofit
            .Builder()
            .baseUrl(mockServer.url(""))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

            .create(BookSearchService::class.java)
    }

    @Test
    fun `책 제목으로 검색한 데이터를 불러옵니다`() = runBlocking {
        val query = "미움받을 용기"
        val json = File("src/test/resources/books.json").readText()
        val response = MockResponse().setBody(json)
        mockServer.enqueue(response)

        val actual = service.searchBookByTitle(query = query)
        val expected = moshi
            .adapter(BooksResponse::class.java)
            .fromJson(json)

        assertEquals(expected, actual)
    }
}
