package com.lmorda.homework.ui.details

import com.lmorda.homework.domain.model.Vehicle

interface DetailsContract {

    sealed class State {

        data object Initial : State()

        data object Loading : State()

        data class Loaded(
            val vehicle: Vehicle
        ) : State()

        // TODO: Improve error messages
        data class LoadError(
            val errorMessage: String?
        ) : State()
    }

    sealed class Event {

        data object OnLoadDetails : Event()

        sealed class Internal : Event() {
            data class OnLoaded(
                val vehicle: Vehicle
            ) : Event()

            data class OnLoadError(
                val errorMessage: String?
            ) : Internal()
        }
    }
}
