package com.lmorda.homework.data.di

import android.content.Context
import android.content.SharedPreferences
import com.lmorda.homework.data.credentials.SHARED_PREF_FILENAME
import com.lmorda.homework.data.credentials.TokenDataStoreImpl
import com.lmorda.homework.data.sharedPrefs.HomeworkSharedPrefsImpl
import com.lmorda.homework.domain.credentials.TokenDataStore
import com.lmorda.homework.domain.sharedPrefs.HomeworkSharedPrefs
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TokenStoreModule {

    @Binds
    @Singleton
    abstract fun bindTokenDataStore(
        impl: TokenDataStoreImpl
    ): TokenDataStore

    @Binds
    @Singleton
    abstract fun bindHomeworkSharedPrefs(
        impl: HomeworkSharedPrefsImpl
    ): HomeworkSharedPrefs
}

@Module
@InstallIn(SingletonComponent::class)
object SharedPrefsModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREF_FILENAME, Context.MODE_PRIVATE)
    }
}
