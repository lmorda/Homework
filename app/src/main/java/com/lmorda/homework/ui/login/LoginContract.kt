package com.lmorda.homework.ui.login

interface LoginContract {

    sealed class State {
        data object Initial : State()

        data object LoggingIn : State()

        data object LoggedIn : State()

        data object LoginError : State()
    }

    sealed class Event {
        data object OnLoggedIn : Event()

        data object OnLoginError : Event()

        sealed class Internal : Event() {

            data class OnLogin(
                val username: String,
                val password: String,
            ) : Internal()
        }
    }
}
