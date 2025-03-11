package com.lmorda.homework.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DriverDto(

    @SerialName("full_name")
    val fullName: String? = null,
)
