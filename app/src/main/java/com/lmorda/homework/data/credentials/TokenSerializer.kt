package com.lmorda.homework.data.credentials

import androidx.datastore.core.Serializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object TokenSerializer: Serializer<UserToken> {
    override val defaultValue: UserToken
        get() = UserToken()

    override suspend fun readFrom(input: InputStream): UserToken {
        val data = withContext(Dispatchers.IO) { input.use { it.readBytes() } }
        val decrypted = data.decodeToString().decryptBase64()
        return Json.decodeFromString(decrypted.decodeToString())
    }

    override suspend fun writeTo(t: UserToken, output: OutputStream) {
        val json = Json.encodeToString(t)
        val encrypted = json.encodeToByteArray().encryptBase64()
        withContext(Dispatchers.IO) { output.use { it.write(encrypted.toByteArray()) } }
    }
}
