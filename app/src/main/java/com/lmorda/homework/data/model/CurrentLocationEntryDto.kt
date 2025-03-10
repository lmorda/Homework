package com.lmorda.homework.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentLocationEntryDto(

    @SerialName("date")
    val date: String,

    @SerialName("geolocation")
    val geolocation: GeolocationDto,
)
