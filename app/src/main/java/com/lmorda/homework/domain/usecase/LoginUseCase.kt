package com.lmorda.homework.domain.usecase

import com.lmorda.homework.domain.credentials.TokenDataStore
import com.lmorda.homework.domain.repository.LoginRepository
import com.lmorda.homework.domain.model.LoginCredentials
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val tokenDataStore: TokenDataStore,
) {
    suspend operator fun invoke(username: String, password: String) {
        try {
            val session = loginRepository.login(LoginCredentials(username, password))
            tokenDataStore.setToken(session.accessToken)
        } catch (ex: Exception) {
            throw ex
        }
    }
}
