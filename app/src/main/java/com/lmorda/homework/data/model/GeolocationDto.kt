package com.lmorda.homework.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeolocationDto(

    @SerialName("latitude")
    val latitude: Double,

    @SerialName("longitude")
    val longitude: Double,
)
