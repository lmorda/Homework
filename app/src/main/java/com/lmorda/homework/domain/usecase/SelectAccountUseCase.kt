package com.lmorda.homework.domain.usecase

import com.lmorda.homework.domain.DataRepository
import javax.inject.Inject

class SelectAccountUseCase @Inject constructor(
    private val dataRepository: DataRepository,
) {

    suspend operator fun invoke(accountName: String) {
        dataRepository.selectAccount(
            accountName = accountName,
        )
    }
}
