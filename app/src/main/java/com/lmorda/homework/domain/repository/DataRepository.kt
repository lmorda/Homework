package com.lmorda.homework.domain.repository

import com.lmorda.homework.domain.filters.VehicleFilter
import com.lmorda.homework.domain.filters.VehicleSort
import com.lmorda.homework.domain.model.Vehicle
import com.lmorda.homework.domain.model.Vehicles

interface DataRepository {

    suspend fun getVehicles(
        startCursor: String?,
        sort: VehicleSort?,
        filter: VehicleFilter?
    ): Vehicles

    suspend fun getVehicle(id: Long): Vehicle

    suspend fun updateVehicle(id: Long, vehicle: Vehicle): Vehicle

}
