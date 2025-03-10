package com.lmorda.homework

import com.lmorda.homework.data.DataRepositoryImpl
import com.lmorda.homework.data.api.ApiService
import com.lmorda.homework.data.api.FILTER_NAME_LIKE
import com.lmorda.homework.data.api.SORT_ASCENDING
import com.lmorda.homework.data.api.SORT_NAME
import com.lmorda.homework.data.api.VEHICLES_PER_PAGE
import com.lmorda.homework.data.mapper.DataMapper
import com.lmorda.homework.data.model.mockApiData
import com.lmorda.homework.domain.filters.VehicleFilter
import com.lmorda.homework.domain.filters.VehicleSort
import com.lmorda.homework.domain.model.mockDomainData
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class DataRepositoryImplTest {

    @Test
    fun `getVehicles should map sort filter query and return mapped vehicles`() = runTest {
        val mockApiService = mockk<ApiService>()

        val dataMapper = mockk<DataMapper> {
            every { map(vehiclesDto = mockApiData) } returns mockDomainData
            every { map(filter = VehicleFilter.NameLike("Vehicle-001")) } returns Pair(
                FILTER_NAME_LIKE,
                "Vehicle-001"
            )
            every { map(sort = VehicleSort.NAME_ASCENDING) } returns Pair(SORT_NAME, SORT_ASCENDING)
        }

        val dataRepository = DataRepositoryImpl(
            apiService = mockApiService,
            mapper = dataMapper,
        )

        coEvery {
            mockApiService.getVehicles(
                perPage = VEHICLES_PER_PAGE,
                startCursor = null,
                sortFilterMap = mapOf(
                    SORT_NAME to SORT_ASCENDING,
                    FILTER_NAME_LIKE to "Vehicle-001",
                )
            )
        } returns mockApiData
        val repos = dataRepository.getVehicles(
            startCursor = null,
            sort = VehicleSort.NAME_ASCENDING,
            filter = VehicleFilter.NameLike("Vehicle-001"),
        )
        assertEquals(mockDomainData, repos)
    }

    @Test
    fun `getVehicle should return mapped vehicle`() = runTest {
        val mockApiService = mockk<ApiService>()

        val dataMapper = mockk<DataMapper> {
            every { map(vehicleDto = mockApiData.records[0]) } returns mockDomainData.records[0]
        }

        val dataRepository = DataRepositoryImpl(
            apiService = mockApiService,
            mapper = dataMapper,
        )

        coEvery { mockApiService.getVehicle(id = 0) } returns mockApiData.records[0]
        val repos = dataRepository.getVehicle(id = 0)
        assertEquals(mockDomainData.records[0], repos)
    }

    @Test
    fun `updateVehicle should return the mapped updated vehicle`() = runTest {
        val mockApiService = mockk<ApiService>()

        val updatedNameVehicle = mockDomainData.records[0].copy(name = "Test")
        val updatedNameVehicleDto = mockApiData.records[0].copy(name = "Test")

        val dataMapper = mockk<DataMapper> {
            every { map(vehicleDto = updatedNameVehicleDto) } returns updatedNameVehicle
            every { map(vehicle = updatedNameVehicle) } returns updatedNameVehicleDto
        }

        val dataRepository = DataRepositoryImpl(
            apiService = mockApiService,
            mapper = dataMapper,
        )
        coEvery {
            mockApiService.updateVehicle(
                id = 0,
                vehicleDto = updatedNameVehicleDto,
            )
        } returns updatedNameVehicleDto
        val repos = dataRepository.updateVehicle(
            id = 0,
            vehicle = updatedNameVehicle,
        )
        assertEquals(updatedNameVehicle, repos)
    }

    // TODO: Add unit test for get list of comments
}
