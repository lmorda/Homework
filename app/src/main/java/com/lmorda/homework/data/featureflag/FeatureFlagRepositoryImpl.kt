package com.lmorda.homework.data.featureflag

import com.lmorda.homework.domain.featureflag.FeatureFlag
import com.lmorda.homework.domain.featureflag.FeatureFlagRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FeatureFlagRepositoryImpl @Inject constructor() : FeatureFlagRepository {
    override fun isFeatureEnabled(flagName: FeatureFlag): Flow<Boolean> = flow {
        emit(
            when (flagName) {
                FeatureFlag.Placeholder -> false
                FeatureFlag.ShowContacts -> true
            }
        )
    }
}
