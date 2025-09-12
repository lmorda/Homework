package com.lmorda.homework.domain.model

data class AuthToken(
  val accessToken: String,
  val tokenType: String? = null,
  val refreshToken: String? = null,
  val scope: Set<String> = emptySet(),
  val issuedAtEpochSeconds: Long? = null,
  val ttlSeconds: Long? = null
) {

  fun isExpired(currentEpochSeconds: Long = System.currentTimeMillis() / 1000): Boolean =
    expiresAtEpochSeconds()?.let { currentEpochSeconds >= it } ?: false

  private fun expiresAtEpochSeconds(): Long? =
    issuedAtEpochSeconds?.let { issued ->
      ttlSeconds?.let { issued + it }
    }

}
