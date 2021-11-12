package com.beok.common.util

import java.text.SimpleDateFormat
import java.util.Date

object DateConverter {

    private val yyyyMMFormat = SimpleDateFormat("yyyy년 MM월")

    fun toYYYYMM(date: Date): String = yyyyMMFormat.format(date)
}
