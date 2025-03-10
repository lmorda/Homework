package com.lmorda.homework.data.api.interceptor

import com.lmorda.homework.data.api.HEADER_ACCOUNT_TOKEN
import okhttp3.Interceptor
import okhttp3.Response

class AccountTokenInterceptor(private val accountToken: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader(HEADER_ACCOUNT_TOKEN, accountToken)
            .build()
        return chain.proceed(request)
    }
}
