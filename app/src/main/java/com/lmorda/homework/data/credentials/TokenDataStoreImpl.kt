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

    override suspend fun getToken(): String? = store.data.first().token

    override suspend fun setToken(token: String?) {
        store.updateData { it.copy(token = token) }
    }

    override suspend fun clearToken() {
        store.updateData { UserToken() }
    }
}
