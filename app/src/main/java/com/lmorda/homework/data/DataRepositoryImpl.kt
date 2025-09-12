package com.lmorda.homework.data

import com.lmorda.homework.data.api.ApiService
import com.lmorda.homework.data.api.VEHICLES_PER_PAGE
import com.lmorda.homework.data.api.result.safeApiCall
import com.lmorda.homework.data.mapper.DataMapper
import com.lmorda.homework.domain.repository.DataRepository
import com.lmorda.homework.domain.filters.VehicleFilter
import com.lmorda.homework.domain.filters.VehicleSort
import com.lmorda.homework.domain.model.Vehicle
import com.lmorda.homework.domain.model.Vehicles
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: DataMapper,
) : DataRepository {

    override suspend fun getVehicles(
        startCursor: String?,
        sort: VehicleSort?,
        filter: VehicleFilter?,
    ): Vehicles {
        return safeApiCall(
            apiCall = {
                val sortFilterMap = mutableMapOf<String, String>()
                if (sort != null) {
                    val sortMap = mapper.map(sort = sort)
                    sortFilterMap[sortMap.first] = sortMap.second
                }
                if (filter != null) {
                    val filterMap = mapper.map(filter = filter)
                    sortFilterMap[filterMap.first] = filterMap.second
                }
                apiService.getVehicles(
                    perPage = VEHICLES_PER_PAGE,
                    startCursor = startCursor,
                    sortFilterMap = sortFilterMap,
                )
            },
            transform = mapper::map
        )
    }

    override suspend fun getVehicle(id: Long) =
        safeApiCall(
            apiCall = { apiService.getVehicle(id = id) },
            transform = mapper::map,
        )

    override suspend fun updateVehicle(id: Long, vehicle: Vehicle) =
        safeApiCall(
            apiCall = { apiService.updateVehicle(id = id, mapper.map(vehicle = vehicle)) },
            transform = mapper::map,
        )
}
