package com.lmorda.homework.domain.repository

import com.lmorda.homework.domain.model.Account
import com.lmorda.homework.domain.model.AuthToken
import com.lmorda.homework.domain.model.LoginCredentials

interface LoginRepository {

    suspend fun login(credentials: LoginCredentials): AuthToken
    suspend fun getAccounts(): List<Account>
    suspend fun selectAccount(id: Long)

}
