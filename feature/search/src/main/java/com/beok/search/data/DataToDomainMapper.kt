package com.beok.search.data

internal interface DataToDomainMapper<T> {
    fun toDto(): T
}

internal fun <T> List<DataToDomainMapper<T>>.toDto(): List<T> = map { it.toDto() }
