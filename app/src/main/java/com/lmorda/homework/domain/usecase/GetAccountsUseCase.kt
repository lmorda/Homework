package com.lmorda.homework.domain.usecase

import com.lmorda.homework.domain.repository.LoginRepository
import javax.inject.Inject

class GetAccountsUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
) {
    suspend operator fun invoke() = loginRepository.getAccounts()
}
