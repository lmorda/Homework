package com.lmorda.homework.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDto(
  @SerialName("client_id")
  val clientId: String?,

  @SerialName("client_secret")
  val clientSecret: String?,

  @SerialName("grant_type")
  val grantType: String,

  @SerialName("login")
  val login: String,

  @SerialName("password")
  val password: String,
)
