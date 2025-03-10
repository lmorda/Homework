package com.lmorda.homework.domain.filters

// TODO: Add more sorting and filtering options

enum class VehicleSort {
    NAME_ASCENDING,
    NAME_DESCENDING,
}

sealed class VehicleFilter {
    data class NameLike(val value: String) : VehicleFilter()
}
