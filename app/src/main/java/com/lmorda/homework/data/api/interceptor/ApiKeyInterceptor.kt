package com.lmorda.homework.data.api.interceptor

import com.lmorda.homework.data.api.HEADER_AUTHORIZATION
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader(HEADER_AUTHORIZATION, apiKey)
            .build()
        return chain.proceed(request)
    }
}
