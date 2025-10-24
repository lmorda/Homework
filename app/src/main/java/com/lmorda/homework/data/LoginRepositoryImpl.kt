package com.lmorda.homework.data

import com.lmorda.homework.data.api.ApiService
import com.lmorda.homework.data.api.OAuthApiService
import com.lmorda.homework.data.api.result.safeApiCall
import com.lmorda.homework.data.credentials.SHARED_PREF_ACCOUNT_ID
import com.lmorda.homework.data.mapper.DataMapper
import com.lmorda.homework.domain.model.Account
import com.lmorda.homework.domain.repository.LoginRepository
import com.lmorda.homework.domain.model.AuthToken
import com.lmorda.homework.domain.model.LoginCredentials
import com.lmorda.homework.domain.sharedPrefs.HomeworkSharedPrefs
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val oAuthApiService: OAuthApiService,
    private val apiService: ApiService,
    private val sharedPrefs: HomeworkSharedPrefs,
    private val dataMapper: DataMapper,
) : LoginRepository {

    override suspend fun login(credentials: LoginCredentials): AuthToken {
        val dto = dataMapper.map(loginCredentials = credentials)
        return safeApiCall(
            apiCall = {
                oAuthApiService.login(loginRequestDto = dto)
            },
            transform = dataMapper::map
        )
    }

    override suspend fun getAccounts(): List<Account> {
        return safeApiCall(
            apiCall = {
                apiService.getAccounts()
            },
            transform = dataMapper::map
        )
    }

    override suspend fun selectAccount(id: Long) {
        sharedPrefs.putLong(key = SHARED_PREF_ACCOUNT_ID, value = id)
    }
}
