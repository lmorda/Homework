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

val mockContactsDomainDataPage1 = listOf(
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
    ),
    Contact(
        name = "Ava Thompson",
        email = "ava.thompson@example.com",
        defaultImageUrl = "https://example.com/image5.jpg",
        createdAt = "2025-03-01T08:30:00.000-08:00"
    ),
    Contact(
        name = "Daniel Anderson",
        email = "daniel.anderson@example.com",
        defaultImageUrl = null,
        createdAt = "2025-02-25T17:20:00.000-08:00"
    ),
    Contact(
        name = "Isabella Moore",
        email = "isabella.moore@example.com",
        defaultImageUrl = "https://example.com/image6.jpg",
        createdAt = "2025-01-28T13:15:00.000-08:00"
    ),
    Contact(
        name = "Benjamin Jackson",
        email = "benjamin.jackson@example.com",
        defaultImageUrl = null,
        createdAt = "2025-04-04T09:05:00.000-08:00"
    ),
    Contact(
        name = "Mia White",
        email = "mia.white@example.com",
        defaultImageUrl = "https://example.com/image7.jpg",
        createdAt = "2025-03-30T14:45:00.000-08:00"
    ),
    Contact(
        name = "Logan Harris",
        email = "logan.harris@example.com",
        defaultImageUrl = null,
        createdAt = "2025-02-12T10:10:00.000-08:00"
    ),
    Contact(
        name = "Charlotte Martin",
        email = "charlotte.martin@example.com",
        defaultImageUrl = "https://example.com/image8.jpg",
        createdAt = "2025-03-25T11:11:00.000-08:00"
    ),
    Contact(
        name = "Henry Thompson",
        email = "henry.thompson@example.com",
        defaultImageUrl = null,
        createdAt = "2025-01-10T15:00:00.000-08:00"
    ),
    Contact(
        name = "Amelia Garcia",
        email = "amelia.garcia@example.com",
        defaultImageUrl = "https://example.com/image9.jpg",
        createdAt = "2025-03-03T13:30:00.000-08:00"
    ),
    Contact(
        name = "Lucas Martinez",
        email = "lucas.martinez@example.com",
        defaultImageUrl = null,
        createdAt = "2025-02-05T12:12:12.000-08:00"
    )
)

val mockContactsDomainDataPage2 = listOf(
    Contact(
        name = "Ella Robinson",
        email = "ella.robinson@example.com",
        defaultImageUrl = "https://example.com/image10.jpg",
        createdAt = "2025-03-07T10:22:00.000-08:00"
    ),
    Contact(
        name = "Jack Clark",
        email = "jack.clark@example.com",
        defaultImageUrl = null,
        createdAt = "2025-02-18T14:40:00.000-08:00"
    ),
    Contact(
        name = "Lily Lewis",
        email = "lily.lewis@example.com",
        defaultImageUrl = "https://example.com/image11.jpg",
        createdAt = "2025-01-31T09:30:00.000-08:00"
    ),
    Contact(
        name = "Noah Lee",
        email = "noah.lee@example.com",
        defaultImageUrl = null,
        createdAt = "2025-03-20T16:20:00.000-08:00"
    ),
    Contact(
        name = "Grace Walker",
        email = "grace.walker@example.com",
        defaultImageUrl = "https://example.com/image12.jpg",
        createdAt = "2025-04-05T13:35:00.000-08:00"
    ),
    Contact(
        name = "Lucas Hall",
        email = "lucas.hall@example.com",
        defaultImageUrl = null,
        createdAt = "2025-02-08T10:15:00.000-08:00"
    ),
    Contact(
        name = "Chloe Allen",
        email = "chloe.allen@example.com",
        defaultImageUrl = "https://example.com/image13.jpg",
        createdAt = "2025-03-28T15:50:00.000-08:00"
    ),
    Contact(
        name = "Mason Young",
        email = "mason.young@example.com",
        defaultImageUrl = null,
        createdAt = "2025-02-14T08:25:00.000-08:00"
    ),
    Contact(
        name = "Avery Hernandez",
        email = "avery.hernandez@example.com",
        defaultImageUrl = "https://example.com/image14.jpg",
        createdAt = "2025-03-16T17:00:00.000-08:00"
    ),
    Contact(
        name = "Sebastian King",
        email = "sebastian.king@example.com",
        defaultImageUrl = null,
        createdAt = "2025-01-29T12:10:00.000-08:00"
    ),
    Contact(
        name = "Harper Wright",
        email = "harper.wright@example.com",
        defaultImageUrl = "https://example.com/image15.jpg",
        createdAt = "2025-03-13T11:45:00.000-08:00"
    ),
    Contact(
        name = "Ethan Lopez",
        email = "ethan.lopez@example.com",
        defaultImageUrl = null,
        createdAt = "2025-04-06T09:55:00.000-08:00"
    ),
    Contact(
        name = "Abigail Hill",
        email = "abigail.hill@example.com",
        defaultImageUrl = "https://example.com/image16.jpg",
        createdAt = "2025-02-20T14:05:00.000-08:00"
    ),
    Contact(
        name = "Logan Scott",
        email = "logan.scott@example.com",
        defaultImageUrl = null,
        createdAt = "2025-03-24T13:12:00.000-08:00"
    ),
    Contact(
        name = "Sofia Green",
        email = "sofia.green@example.com",
        defaultImageUrl = "https://example.com/image17.jpg",
        createdAt = "2025-01-27T16:45:00.000-08:00"
    ),
    Contact(
        name = "Jacob Adams",
        email = "jacob.adams@example.com",
        defaultImageUrl = null,
        createdAt = "2025-02-16T12:22:00.000-08:00"
    ),
    Contact(
        name = "Aria Nelson",
        email = "aria.nelson@example.com",
        defaultImageUrl = "https://example.com/image18.jpg",
        createdAt = "2025-03-09T15:38:00.000-08:00"
    ),
    Contact(
        name = "Matthew Carter",
        email = "matthew.carter@example.com",
        defaultImageUrl = null,
        createdAt = "2025-04-03T10:00:00.000-08:00"
    ),
    Contact(
        name = "Scarlett Mitchell",
        email = "scarlett.mitchell@example.com",
        defaultImageUrl = "https://example.com/image19.jpg",
        createdAt = "2025-03-11T11:59:00.000-08:00"
    ),
    Contact(
        name = "Alexander Perez",
        email = "alexander.perez@example.com",
        defaultImageUrl = null,
        createdAt = "2025-02-22T13:18:00.000-08:00"
    )
)

val mockContactsDomainData = listOf(
    Contacts(
        nextCursor = "mockContactsDomainDataPage2",
        contacts = mockContactsDomainDataPage1,
    ),
    Contacts(
        nextCursor = null,
        contacts = mockContactsDomainDataPage2,
    )
)
