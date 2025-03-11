package com.lmorda.homework.data.mapper

import com.lmorda.homework.data.api.FILTER_NAME_LIKE
import com.lmorda.homework.data.api.SORT_ASCENDING
import com.lmorda.homework.data.api.SORT_DESCENDING
import com.lmorda.homework.data.api.SORT_NAME
import com.lmorda.homework.data.model.CurrentLocationEntryDto
import com.lmorda.homework.data.model.DriverDto
import com.lmorda.homework.data.model.GeolocationDto
import com.lmorda.homework.data.model.VehicleDto
import com.lmorda.homework.data.model.VehiclesDto
import com.lmorda.homework.domain.filters.VehicleFilter
import com.lmorda.homework.domain.filters.VehicleSort
import com.lmorda.homework.domain.model.CurrentLocationEntry
import com.lmorda.homework.domain.model.Driver
import com.lmorda.homework.domain.model.Geolocation
import com.lmorda.homework.domain.model.Vehicle
import com.lmorda.homework.domain.model.Vehicles
import javax.inject.Inject

class DataMapper @Inject constructor() {

    fun map(vehiclesDto: VehiclesDto) = with(vehiclesDto) {
        Vehicles(
            nextCursor = nextCursor,
            records = records.map { map(it) },
        )
    }

    fun map(vehicleDto: VehicleDto) = with(vehicleDto) {
        Vehicle(
            id = id,
            name = name.orEmpty(),
            make = make.orEmpty(),
            model = model.orEmpty(),
            vehicleTypeName = vehicleTypeName.orEmpty(),
            vehicleStatusName = vehicleStatusName.orEmpty(),
            primaryMeterValue = primaryMeterValue.orEmpty(),
            primaryMeterUnit = primaryMeterUnit.orEmpty(),
            secondaryMeterValue = secondaryMeterValue?.takeIf { it != "0.0" }.orEmpty(),
            secondaryMeterUnit = secondaryMeterUnit.orEmpty(),
            vin = vin.orEmpty(),
            licensePlate = licensePlate.orEmpty(),
            defaultImageUrlSmall = defaultImageUrlSmall.orEmpty(),
            currentLocationEntry = currentLocationEntry?.let { map(it) },
            driver = driver?.let { map(it) },
        )
    }

    fun map(vehicle: Vehicle) = with(vehicle) {
        VehicleDto(
            id = id,
            name = name,
            make = make,
            model = model,
            vehicleTypeName = vehicleTypeName,
            vehicleStatusName = vehicleStatusName,
            primaryMeterValue = primaryMeterValue,
            primaryMeterUnit = primaryMeterUnit,
            secondaryMeterValue = secondaryMeterValue,
            secondaryMeterUnit = secondaryMeterUnit,
            vin = vin,
            licensePlate = licensePlate,
        )
    }

    private fun map(currentLocationEntryDto: CurrentLocationEntryDto) =
        with(currentLocationEntryDto) {
            CurrentLocationEntry(
                date = date.orEmpty(),
                geolocation = geolocation?.let { map(it) }
                    ?: Geolocation(0.0, 0.0),
            )
        }

    private fun map(geolocation: GeolocationDto) = with(geolocation) {
        Geolocation(
            latitude = latitude ?: 0.0,
            longitude = longitude ?: 0.0,
        )
    }

    private fun map(driverDto: DriverDto) = with(driverDto) {
        Driver(fullName = fullName.orEmpty())
    }

    fun map(sort: VehicleSort) = when (sort) {
        VehicleSort.NAME_ASCENDING -> SORT_NAME to SORT_ASCENDING
        VehicleSort.NAME_DESCENDING -> SORT_NAME to SORT_DESCENDING
    }

    fun map(filter: VehicleFilter) = when (filter) {
        is VehicleFilter.NameLike -> FILTER_NAME_LIKE to filter.value
    }
}
