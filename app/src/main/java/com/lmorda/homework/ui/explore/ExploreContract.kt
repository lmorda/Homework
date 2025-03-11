package com.lmorda.homework.ui.explore

import com.lmorda.homework.domain.model.Vehicle

interface ExploreContract {

    sealed class State {

        data object Initial : State()

        data class Loaded(
            val vehicles: List<Vehicle>,
            val nextCursor: String?,
            val nameLike: String?,
        ) : State()

        data class LoadingPage(
            val vehicles: List<Vehicle>,
        ) : State()

        data object LoadingRefresh : State()

        // TODO: Improve error messages
        data class LoadError(
            val errorMessage: String?,
        ) : State()
    }

    sealed class Event {

        data object OnRefresh : Event()
        data object OnLoadNextPage : Event()

        data class OnSearchName(
            val name: String,
        ) : Event()

        sealed class Internal : Event() {
            data class OnLoaded(
                val vehicles: List<Vehicle>,
                val nextCursor: String?,
                val nameLike: String?,
            ) : Internal()

            data class OnLoadError(
                val errorMessage: String?,
            ) : Internal()
        }
    }
}
