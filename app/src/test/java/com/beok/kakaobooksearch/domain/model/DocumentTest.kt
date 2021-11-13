package com.beok.kakaobooksearch.domain.model

import java.util.Date
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DocumentTest {

    @Test
    fun `정가의 할인율을 구합니다`() {
        val document = Document(
            isbn = "",
            thumbnail = "",
            title = "",
            authors = listOf(),
            publisher = "",
            datetime = Date(0),
            salePrice = 13410,
            price = 14900,
            contents = ""
        )
        val actual = document.salePercent()

        assertThat(actual).isEqualTo(10)
    }

    @Test
    fun `할인가가 0보다 작을 경우 할인율이 0%가 됩니다`() {
        val document = Document(
            isbn = "",
            thumbnail = "",
            title = "",
            authors = listOf(),
            publisher = "",
            datetime = Date(0),
            salePrice = -1,
            price = 14900,
            contents = ""
        )
        val actual = document.salePercent()

        assertThat(actual).isEqualTo(0)
    }
}
