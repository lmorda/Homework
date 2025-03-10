package com.lmorda.homework.domain.model

data class Vehicle(
    val id: Long,
    val name: String,
    val make: String,
    val model: String,
    val vehicleTypeName: String,
    val vehicleStatusName: String,
    val primaryMeterValue: String,
    val primaryMeterUnit: String,
    val secondaryMeterValue: String,
    val secondaryMeterUnit: String,
    val vin: String,
    val licensePlate: String,
    val driver: Driver?,
    val defaultImageUrlSmall: String?,
    val currentLocationEntry: CurrentLocationEntry?,
)
