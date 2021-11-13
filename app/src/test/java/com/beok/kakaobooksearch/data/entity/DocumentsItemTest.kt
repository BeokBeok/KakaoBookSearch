package com.beok.kakaobooksearch.data.entity

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DocumentsItemTest {

    @Test
    fun `Response를 Dto로 변환합니다`() {
        val response = DocumentsItem(
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

        val actual = response.toDto()

        assertThat(actual).isEqualTo(response.toDto())
    }
}
