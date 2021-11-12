package com.beok.kakaobooksearch.search.data.entity

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BooksResponseTest {

    @Test
    fun `Response를 Dto로 변환합니다`() {
        val response = BooksResponse(
            documents = listOf(
                DocumentsItem(
                    datetime = null,
                    thumbnail = null,
                    translators = listOf(),
                    contents = null,
                    price = null,
                    isbn = null,
                    publisher = null,
                    title = null,
                    salePrice = null,
                    url = null,
                    authors = listOf(),
                    status = null
                )
            ),
            meta = Meta(
                totalCount = 15,
                isEnd = false,
                pageableCount = 13
            )
        )
        val actual = response.toDto()

        assertThat(actual).isEqualTo(response.toDto())
    }
}
