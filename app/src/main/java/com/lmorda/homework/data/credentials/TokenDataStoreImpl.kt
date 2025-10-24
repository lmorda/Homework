package com.lmorda.homework.data.credentials

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.lmorda.homework.domain.credentials.TokenDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

private val Context.userTokenDataStore: DataStore<UserToken> by dataStore(
    fileName = USER_TOKEN_FILENAME,
    serializer = TokenSerializer
)

@Singleton
class TokenDataStoreImpl @Inject constructor(
    @ApplicationContext context: Context
) : TokenDataStore {

    private val store = context.userTokenDataStore

    override suspend fun getOauthToken(): String? =
        store.data.first().oauthToken

    override suspend fun setOauthToken(token: String?) {
        store.updateData { it.copy(oauthToken = token?.trim()) }
    }

    override suspend fun getAccountToken(): String? =
        store.data.first().accountToken

    override suspend fun setAccountToken(token: String?) {
        store.updateData { it.copy(accountToken = token?.trim()) }
    }

    override suspend fun clearOauthToken() {
        store.updateData { it.copy(oauthToken = null) }
    }

    override suspend fun clearAccountToken() {
        store.updateData { it.copy(accountToken = null) }
    }
}