package com.lmorda.homework.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.lmorda.homework.data.api.ApiService
import com.lmorda.homework.data.api.BASE_URL
import com.lmorda.homework.data.api.CONTENT_TYPE
import com.lmorda.homework.data.api.OAuthApiService
import com.lmorda.homework.data.api.interceptor.TokenInterceptor
import com.lmorda.homework.domain.credentials.TokenDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideKotlinxJson(): Json = Json { ignoreUnknownKeys = true }

    @Provides
    @Singleton
    fun provideJsonConverterFactory(json: Json): Converter.Factory =
        json.asConverterFactory(CONTENT_TYPE.toMediaType())

    @Provides
    @Singleton
    fun provideOkHttpClient(
        tokenDataStore: TokenDataStore
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .addInterceptor(TokenInterceptor(tokenDataStore))
            .build()


    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()

    @Provides
    @Singleton
    fun provideOAuthApiService(
        retrofit: Retrofit,
    ): OAuthApiService =
        retrofit.create(OAuthApiService::class.java)

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)
}
