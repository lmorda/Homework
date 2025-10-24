package com.lmorda.homework.data.credentials

import kotlinx.serialization.Serializable

@Serializable
data class UserToken(
    val oauthToken: String? = null,
    val accountToken: String? = null,
)
