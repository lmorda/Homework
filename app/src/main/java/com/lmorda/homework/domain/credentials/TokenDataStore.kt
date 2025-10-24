package com.lmorda.homework.domain.credentials

interface TokenDataStore {
    suspend fun getOauthToken(): String?
    suspend fun setOauthToken(token: String?)
    suspend fun getAccountToken(): String?
    suspend fun setAccountToken(token: String?)
    suspend fun clearOauthToken()
    suspend fun clearAccountToken()
}