package com.lmorda.homework.data.di

import com.lmorda.homework.data.credentials.TokenDataStoreImpl
import com.lmorda.homework.data.sharedPrefs.HomeworkSharedPrefsImpl
import com.lmorda.homework.domain.credentials.TokenDataStore
import com.lmorda.homework.domain.sharedPrefs.HomeworkSharedPrefs
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TokenStoreModule {

    @Binds
    @Singleton
    abstract fun bindHomeworkSharedPrefs(
        impl: HomeworkSharedPrefsImpl
    ): HomeworkSharedPrefs

    @Binds
    @Singleton
    abstract fun bindTokenDataStore(
        impl: TokenDataStoreImpl
    ): TokenDataStore
}
