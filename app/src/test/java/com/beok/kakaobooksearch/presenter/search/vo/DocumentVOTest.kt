package com.beok.kakaobooksearch.presenter.search.vo

import com.beok.kakaobooksearch.domain.model.Document
import java.util.Date
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DocumentVOTest {

    @Test
    fun `domain 모델을 vo로 변환합니다`() {
        val model = Document(
            isbn = "",
            thumbnail = "",
            title = "",
            authors = listOf(),
            publisher = "",
            datetime = Date(0),
            salePrice = 0,
            price = 0,
            contents = ""
        )
        val actual = DocumentVO.fromDomain(model)

        assertThat(actual).isEqualTo(DocumentVO.fromDomain(model))
    }
}
