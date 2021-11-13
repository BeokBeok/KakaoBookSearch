package com.beok.kakaobooksearch.util

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class EventTest {

    @Test
    fun `처음 이후에 호출 시 null을 리턴합니다`() {
        val event = Event(false)

        event.getContentIfNotHandled()

        val actual = event.getContentIfNotHandled()

        assertThat(actual).isNull()
    }
}
