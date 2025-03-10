package com.lmorda.homework.data.api

import com.lmorda.homework.data.model.VehicleDto
import com.lmorda.homework.data.model.VehiclesDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiService {

    @GET("vehicles")
    suspend fun getVehicles(
        @Query("per_page") perPage: Int,
        @Query("start_cursor") startCursor: String?,
        @QueryMap(encoded = true) sortFilterMap: Map<String, String>,
    ): VehiclesDto

    @GET("vehicles/{id}")
    suspend fun getVehicle(
        @Path("id") id: Long,
    ): VehicleDto

    @PUT("vehicles/{id}")
    suspend fun updateVehicle(
        @Path("id") id: Long,
        @Body vehicleDto: VehicleDto,
    ): VehicleDto

    // TODO: Add GET list of comments
}
