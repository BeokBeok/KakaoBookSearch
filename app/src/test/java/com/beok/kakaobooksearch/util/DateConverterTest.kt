package com.beok.kakaobooksearch.util

import java.util.Date
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DateConverterTest {

    @Test
    fun `Date를 yyyy년 MM월로 변환합니다`() {
        val date = Date(1636721041983) // 2021-11-12
        val expected = "2021년 11월"
        val actual = DateConverter.toYYYYMM(date)

        assertThat(actual).isEqualTo(expected)
    }
}
