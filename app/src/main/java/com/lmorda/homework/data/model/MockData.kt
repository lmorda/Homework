package com.lmorda.homework.data.model

val mockApiData = VehiclesDto(
    nextCursor = null,
    perPage = 50,
    records = listOf(
        VehicleDto(
            id = 3885087,
            name = "Vehicle-001",
            make = "Toyota",
            model = "Tundra",
            driver = DriverDto(
                fullName = "John Driver"
            ),
            vehicleTypeName = "Pickup Truck",
            vehicleStatusName = "Active",
            primaryMeterValue = "200.0",
            primaryMeterUnit = "mi",
            secondaryMeterValue = "200.0",
            secondaryMeterUnit = "mi",
            vin = "1234567890",
            licensePlate = "LICENSE",
            defaultImageUrlSmall = "https://photos.com/100",
            currentLocationEntry = CurrentLocationEntryDto(
                date = "2025-03-14T12:46:27.000-07:00",
                geolocation = GeolocationDto(
                    latitude = 37.7749,
                    longitude = -122.4194
                ),
            ),
        ),
        VehicleDto(
            id = 3885090,
            name = "Vehicle-002",
            make = "Toyota",
            model = "Tundra",
            driver = DriverDto(
                fullName = "John Driver"
            ),
            vehicleTypeName = "Pickup Truck",
            vehicleStatusName = "Active",
            primaryMeterValue = "200.0",
            primaryMeterUnit = "mi",
            secondaryMeterValue = "200.0",
            secondaryMeterUnit = "mi",
            vin = "1234567890",
            licensePlate = "LICENSE",
            defaultImageUrlSmall = "https://photos.com/100",
            currentLocationEntry = CurrentLocationEntryDto(
                date = "2025-03-14T12:46:27.000-07:00",
                geolocation = GeolocationDto(
                    latitude = 37.7749,
                    longitude = -122.4194
                ),
            ),
        ),
        VehicleDto(
            id = 3885091,
            name = "Vehicle-003",
            make = "Toyota",
            model = "Tundra",
            driver = DriverDto(
                fullName = "John Driver"
            ),
            vehicleTypeName = "Car",
            vehicleStatusName = "Active",
            primaryMeterValue = "0.0",
            primaryMeterUnit = "mi",
            secondaryMeterValue = null,
            secondaryMeterUnit = null,
            vin = null,
            licensePlate = null,
            currentLocationEntry = null,
        ),
    )
)
