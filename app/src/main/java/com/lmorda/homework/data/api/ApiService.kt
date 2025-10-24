package com.lmorda.homework.data.api

import com.lmorda.homework.data.model.AccountsDto
import com.lmorda.homework.data.model.VehicleDto
import com.lmorda.homework.data.model.VehiclesDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiService {

    @GET("api/v1/accounts")
    suspend fun getAccounts(
        @Query("include_permissions") includePermissions: Boolean = true,
        @Query("include_navigation_permissions") includeNavigationPermissions: Boolean = true,
        @Query("include_features") includeFeatures: Boolean = true
    ): AccountsDto

    @GET("api/v1/vehicles")
    suspend fun getVehicles(
        @Query("per_page") perPage: Int,
        @Query("start_cursor") startCursor: String?,
        @QueryMap(encoded = true) sortFilterMap: Map<String, String>,
    ): VehiclesDto

    @GET("api/v1/vehicles/{id}")
    suspend fun getVehicle(
        @Path("id") id: Long,
    ): VehicleDto

    @PUT("api/v1/vehicles/{id}")
    suspend fun updateVehicle(
        @Path("id") id: Long,
        @Body vehicleDto: VehicleDto,
    ): VehicleDto

    // TODO: Add GET list of comments
}
