package com.lmorda.homework.data.credentials

import java.util.Base64

fun ByteArray.encryptBase64(): String =
    Base64.getEncoder().encodeToString(HomeworkEncryptor.encrypt(this))

fun String.decryptBase64(): ByteArray =
    HomeworkEncryptor.decrypt(Base64.getDecoder().decode(this))
