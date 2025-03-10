package com.lmorda.homework.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VehiclesDto(
    @SerialName("next_cursor")
    val nextCursor: String?,

    @SerialName("per_page")
    val perPage: Int,

    @SerialName("records")
    val records: List<VehicleDto>,
)
