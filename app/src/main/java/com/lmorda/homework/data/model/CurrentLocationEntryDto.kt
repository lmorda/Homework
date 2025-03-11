package com.lmorda.homework.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentLocationEntryDto(

    @SerialName("date")
    val date: String? = null,

    @SerialName("geolocation")
    val geolocation: GeolocationDto? = null,
)
