package com.beok.kakaobooksearch.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateConverter {

    private val yyyyMMFormat = SimpleDateFormat("yyyy년 MM월", Locale.KOREAN)

    fun toYYYYMM(date: Date): String = yyyyMMFormat.format(date)
}
