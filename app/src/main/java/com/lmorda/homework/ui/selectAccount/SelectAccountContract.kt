package com.lmorda.homework.ui.selectAccount

interface SelectAccountContract {

    sealed class State {
        data object Initial : State()
    }

    sealed class Event {
        data object OnAccountSelected : Event()

        sealed class Internal : Event() {
            data class OnAccountSelected(
                val accountName: String,
            ) : Internal()
        }
    }
}