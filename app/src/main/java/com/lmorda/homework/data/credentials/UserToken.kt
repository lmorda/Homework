package com.lmorda.homework.data.credentials

import kotlinx.serialization.Serializable

@Serializable
data class UserToken(
    val token: String? = null
)
