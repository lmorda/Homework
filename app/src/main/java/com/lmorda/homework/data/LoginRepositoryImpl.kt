package com.lmorda.homework.data

import com.lmorda.homework.data.api.OAuthApiService
import com.lmorda.homework.data.api.result.safeApiCall
import com.lmorda.homework.data.mapper.DataMapper
import com.lmorda.homework.domain.repository.LoginRepository
import com.lmorda.homework.domain.model.AuthToken
import com.lmorda.homework.domain.model.LoginCredentials
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val api: OAuthApiService,
    private val dataMapper: DataMapper,
) : LoginRepository {

    override suspend fun login(credentials: LoginCredentials): AuthToken {
        val request = dataMapper.map(loginCredentials = credentials)
        return safeApiCall(
            apiCall = {
                api.loginWithPasswordGrant(loginRequestDto = request)
            },
            transform = dataMapper::map
        )
    }

    override suspend fun selectAccount(accountName: String) {

    }
}
