package com.lmorda.homework.domain.usecase

import com.lmorda.homework.domain.DataRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val dataRepository: DataRepository,
) {
    suspend operator fun invoke(username: String, password: String) {
        dataRepository.login(username = username, password = password)
    }
}
