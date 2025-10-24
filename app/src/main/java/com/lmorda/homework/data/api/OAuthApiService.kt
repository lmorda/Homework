package com.lmorda.homework.data.api

import com.lmorda.homework.data.model.LoginRequestDto
import com.lmorda.homework.data.model.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface OAuthApiService {
    @POST("oauth/token")
    suspend fun login(@Body loginRequestDto: LoginRequestDto): LoginResponseDto
}
