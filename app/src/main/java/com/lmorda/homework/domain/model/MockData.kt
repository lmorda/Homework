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

val mockContactsDomainData = listOf(
    Contact(
        name = "Lou Morda",
        email = "lmorda81@gmail.com",
        defaultImageUrl = "https://d8g9nhlfs6lwh.cloudfront.net/UzeF59YQ6GVHLP2qKeNg?signature=abc123",
        createdAt = "2025-03-05T11:13:10.612-08:00"
    ),
    Contact(
        name = "Kate Morda",
        email = "Kate.Morda@gmail.com",
        defaultImageUrl = null,
        createdAt = "2025-03-06T08:02:36.482-08:00"
    ),
    Contact(
        name = "Jane Doe",
        email = "jane.doe@example.com",
        defaultImageUrl = "https://example.com/image1.jpg",
        createdAt = "2025-01-15T09:21:34.000-08:00"
    ),
    Contact(
        name = "John Smith",
        email = "john.smith@example.com",
        defaultImageUrl = null,
        createdAt = "2025-02-10T14:45:12.000-08:00"
    ),
    Contact(
        name = "Emily Johnson",
        email = "emily.johnson@example.com",
        defaultImageUrl = "https://example.com/image2.jpg",
        createdAt = "2025-04-01T12:34:56.000-08:00"
    ),
    Contact(
        name = "Michael Brown",
        email = "michael.brown@example.com",
        defaultImageUrl = null,
        createdAt = "2025-03-12T16:10:22.000-08:00"
    ),
    Contact(
        name = "Sophia Davis",
        email = "sophia.davis@example.com",
        defaultImageUrl = "https://example.com/image3.jpg",
        createdAt = "2025-03-22T10:05:47.000-08:00"
    ),
    Contact(
        name = "William Wilson",
        email = "william.wilson@example.com",
        defaultImageUrl = null,
        createdAt = "2025-02-28T11:15:30.000-08:00"
    ),
    Contact(
        name = "Olivia Martinez",
        email = "olivia.martinez@example.com",
        defaultImageUrl = "https://example.com/image4.jpg",
        createdAt = "2025-03-18T13:50:18.000-08:00"
    ),
    Contact(
        name = "James Taylor",
        email = "james.taylor@example.com",
        defaultImageUrl = null,
        createdAt = "2025-04-02T15:25:40.000-08:00"
    )
)
