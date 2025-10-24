package com.lmorda.homework.data.api.interceptor

import com.lmorda.homework.data.api.HEADER_ACCOUNT_TOKEN
import com.lmorda.homework.data.api.HEADER_AUTHORIZATION
import com.lmorda.homework.data.api.HEADER_BEARER
import com.lmorda.homework.domain.credentials.TokenDataStore
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(private val tokenStore: TokenDataStore) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request()
        if (req.url.encodedPath.startsWith("/oauth/")) {
            return chain.proceed(req)
        }

        val oauth = runBlocking { tokenStore.getOauthToken() }
        val account = runBlocking { tokenStore.getAccountToken() }

        val b = req.newBuilder()
        if (!oauth.isNullOrBlank()) {
            b.header(HEADER_AUTHORIZATION, "$HEADER_BEARER $oauth")
        }
        if (!account.isNullOrBlank()) {
            b.header(HEADER_ACCOUNT_TOKEN, account)
        }
        return chain.proceed(b.build())
    }
}
