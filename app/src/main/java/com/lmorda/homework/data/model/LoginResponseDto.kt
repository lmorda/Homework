package com.lmorda.homework.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
  @SerialName("access_token")
  val accessToken: String?,

  @SerialName("created_at")
  val createdAt: Long?,

  @SerialName("expires_in")
  val expiresIn: Long?,

  @SerialName("refresh_token")
  val refreshToken: String?,

  @SerialName("scope")
  val scope: String?,

  @SerialName("token_type")
  val tokenType: String?,
)
