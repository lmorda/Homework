package com.lmorda.homework

interface MainContract {

    sealed class State {
        data object Initial : State()

        data class CheckedInitialNavigation(
            val route: String,
        ) : State()
    }

    sealed class Event {
        data class OnCheckNavigation(
            val route: String?,
        ) : Event()
    }
}