package com.lmorda.homework.data.di

import com.lmorda.homework.data.featureflag.FeatureFlagRepositoryImpl
import com.lmorda.homework.domain.featureflag.FeatureFlagRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FeatureFlagModule {

    @Binds
    abstract fun bindFeatureFlagRepository(
        featureFlagRepositoryImpl: FeatureFlagRepositoryImpl
    ): FeatureFlagRepository
}
