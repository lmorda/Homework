package com.lmorda.homework.domain

import com.lmorda.homework.domain.filters.VehicleFilter
import com.lmorda.homework.domain.filters.VehicleSort
import com.lmorda.homework.domain.model.Vehicle
import com.lmorda.homework.domain.model.Vehicles

interface DataRepository {

    suspend fun login(username: String, password: String)

    suspend fun selectAccount(accountName: String)

    suspend fun getVehicles(
        startCursor: String?,
        sort: VehicleSort?,
        filter: VehicleFilter?
    ): Vehicles

    suspend fun getVehicle(id: Long): Vehicle

    suspend fun updateVehicle(id: Long, vehicle: Vehicle): Vehicle

}
