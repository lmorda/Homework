package com.lmorda.homework

import com.lmorda.homework.domain.DataRepository
import com.lmorda.homework.domain.filters.VehicleFilter
import com.lmorda.homework.domain.filters.VehicleSort
import com.lmorda.homework.domain.model.Vehicles
import com.lmorda.homework.domain.usecase.GetVehiclePageUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetVehiclePageUseCaseTest {

    @Test
    fun `GIVEN no filter for vehicle name WHEN get vehicle page THEN query repository with null filter`() = runTest {
        val repository = mockk<DataRepository>()
        val expected = mockk<Vehicles>(relaxed = true)

        // GIVEN repository makes successful API call
        coEvery {
            repository.getVehicles(
                startCursor = "startCursor",
                sort = VehicleSort.NAME_ASCENDING,
                filter = null
            )
        } returns expected

        // WHEN use case is invoked with null vehicle name
        val getVehiclePageUseCase = GetVehiclePageUseCase(repository)
        val vehiclePage = getVehiclePageUseCase(
            startCursor = "startCursor",
            sort = VehicleSort.NAME_ASCENDING,
            nameLike = null
        )

        // THEN use case returns a page of vehicles without being filtered
        assertEquals(expected, vehiclePage)
        coVerify(exactly = 1) {
            repository.getVehicles(
                startCursor = "startCursor",
                sort = VehicleSort.NAME_ASCENDING,
                filter = null
            )
        }
    }

    @Test
    fun `GIVEN filter by vehicle name WHEN get vehicle page THEN query repository with NameLike filter`() = runTest {
        val repository = mockk<DataRepository>()
        val expected = mockk<Vehicles>(relaxed = true)

        // GIVEN repository makes successful API call with NameLike filter
        coEvery {
            repository.getVehicles(
                startCursor = "startCursor",
                sort = VehicleSort.NAME_ASCENDING,
                filter = VehicleFilter.NameLike("Vehicle-001")
            )
        } returns expected

        // WHEN use case is invoked with a vehicle name
        val getVehiclePageUseCase = GetVehiclePageUseCase(repository)
        val vehiclePage = getVehiclePageUseCase(
            startCursor = "startCursor",
            sort = VehicleSort.NAME_ASCENDING,
            nameLike = "Vehicle-001"
        )

        // THEN use case returns a page of vehicles filtered by name
        assertEquals(expected, vehiclePage)
        coVerify(exactly = 1) {
            repository.getVehicles(
                startCursor = "startCursor",
                sort = VehicleSort.NAME_ASCENDING,
                filter = VehicleFilter.NameLike("Vehicle-001")
            )
        }
    }
}
