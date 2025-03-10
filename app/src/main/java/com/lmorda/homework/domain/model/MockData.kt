package com.lmorda.homework.domain.model

val mockDomainData = Vehicles(
    nextCursor = null,
    records = listOf(
        Vehicle(
            id = 3885087,
            name = "Vehicle-001",
            make = "Toyota",
            model = "Tundra",
            driver = Driver(
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
            currentLocationEntry = CurrentLocationEntry(
                date = "2025-03-14T12:46:27.000-07:00",
                geolocation = Geolocation(
                    latitude = 37.7749,
                    longitude = -122.4194
                )
            )
        ),
        Vehicle(
            id = 3885090,
            name = "Vehicle-002",
            make = "Toyota",
            model = "Tundra",
            driver = Driver(
                fullName = "John Driver"
            ),
            vehicleTypeName = "Car",
            vehicleStatusName = "In Shop",
            primaryMeterValue = "0.0",
            primaryMeterUnit = "mi",
            secondaryMeterValue = "0.0",
            secondaryMeterUnit = "",
            vin = "",
            licensePlate = "",
            defaultImageUrlSmall = "https://photos.com/100",
            currentLocationEntry = CurrentLocationEntry(
                date = "2025-03-14T12:46:27.000-07:00",
                geolocation = Geolocation(
                    latitude = 37.7749,
                    longitude = -122.4194
                )
            )
        ),
        Vehicle(
            id = 3885091,
            name = "Vehicle-003",
            driver = Driver(
                fullName = "John Driver"
            ),
            make = "",
            model = "",
            vehicleTypeName = "Car",
            vehicleStatusName = "Active",
            primaryMeterValue = "0.0",
            primaryMeterUnit = "mi",
            secondaryMeterValue = "0.0",
            secondaryMeterUnit = "",
            vin = "",
            licensePlate = "",
            defaultImageUrlSmall = "https://photos.com/100",
            currentLocationEntry = null,
        ),
    )
)
