package com.lmorda.homework.domain.usecase

import com.lmorda.homework.domain.repository.DataRepository
import com.lmorda.homework.domain.filters.VehicleFilter
import com.lmorda.homework.domain.filters.VehicleSort
import com.lmorda.homework.domain.model.Vehicles
import javax.inject.Inject

class GetVehiclePageUseCase @Inject constructor(
    private val dataRepository: DataRepository,
) {

    suspend operator fun invoke(
        startCursor: String?,
        sort: VehicleSort,
        nameLike: String?
    ): Vehicles {
        return dataRepository.getVehicles(
            startCursor = startCursor,
            sort = sort,
            filter = nameLike?.takeIf { it.isNotBlank() }?.let {
                VehicleFilter.NameLike(it)
            },
        )
    }
}
