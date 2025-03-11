package com.lmorda.homework.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VehicleDto(
    @SerialName("id")
    val id: Long,

    @SerialName("name")
    val name: String? = null,

    @SerialName("vehicle_type_name")
    val vehicleTypeName: String? = null,

    @SerialName("vehicle_status_name")
    val vehicleStatusName: String? = null,

    @SerialName("primary_meter_value")
    val primaryMeterValue: String? = null,

    @SerialName("primary_meter_unit")
    val primaryMeterUnit: String? = null,

    @SerialName("secondary_meter_value")
    val secondaryMeterValue: String? = null,

    @SerialName("secondary_meter_unit")
    val secondaryMeterUnit: String? = null,

    @SerialName("make")
    val make: String? = null,

    @SerialName("model")
    val model: String? = null,

    @SerialName("driver")
    val driver: DriverDto? = null,

    @SerialName("vin")
    val vin: String? = null,

    @SerialName("license_plate")
    val licensePlate: String? = null,

    @SerialName("default_image_url_small")
    val defaultImageUrlSmall: String? = null,

    @SerialName("current_location_entry")
    val currentLocationEntry: CurrentLocationEntryDto? = null,
)
