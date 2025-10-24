package com.lmorda.homework.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AccountsDto(
    val records: List<AccountDto>
)
