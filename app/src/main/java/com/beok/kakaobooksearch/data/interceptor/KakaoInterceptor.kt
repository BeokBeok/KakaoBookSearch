package com.beok.kakaobooksearch.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response

internal object KakaoInterceptor : Interceptor {

    private const val HEADER_NAME = "Authorization"
    private const val HEADER_VALUE_PREFIX = "KakaoAK "
    private const val API_KEY = "7152b9bf3a3d93354032271f565d1cfe"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain
            .request()
            .newBuilder()
            .addHeader(HEADER_NAME, HEADER_VALUE_PREFIX + API_KEY)
            .build()
        return chain.proceed(request)
    }
}
