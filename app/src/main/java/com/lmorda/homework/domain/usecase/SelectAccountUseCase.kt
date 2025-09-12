package com.lmorda.homework.domain.usecase

import com.lmorda.homework.domain.repository.LoginRepository
import javax.inject.Inject

class SelectAccountUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
) {

    suspend operator fun invoke(accountName: String) {
        loginRepository.selectAccount(
            accountName = accountName,
        )
    }
}
