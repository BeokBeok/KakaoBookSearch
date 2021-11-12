package com.beok.search.domain.model

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
            price = 14900
        )
        val actual = document.salePercent()

        assertThat(actual).isEqualTo(10)
    }
}
