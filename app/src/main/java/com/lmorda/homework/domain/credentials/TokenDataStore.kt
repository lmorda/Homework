package com.lmorda.homework.domain.credentials

interface TokenDataStore {
    suspend fun getToken(): String?
    suspend fun setToken(token: String?)
    suspend fun clearToken()
}
