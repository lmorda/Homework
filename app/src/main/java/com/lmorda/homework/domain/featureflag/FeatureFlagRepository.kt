package com.lmorda.homework.domain.featureflag

import kotlinx.coroutines.flow.Flow

interface FeatureFlagRepository {
    fun isFeatureEnabled(flagName: FeatureFlag): Flow<Boolean>
}
