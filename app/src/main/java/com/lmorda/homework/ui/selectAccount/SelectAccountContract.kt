package com.lmorda.homework.ui.selectAccount

import com.lmorda.homework.domain.model.Account

interface SelectAccountContract {

    sealed class State {
        data object Initial : State()
        data class AccountsLoaded(
            val accounts: List<Account>,
        ) : State()
        data object AccountLoadError : State()
        data object AccountSelected : State()
    }

    sealed class Event {
        data object OnAccountsError: Event()

        data class OnAccountsLoaded(
            val accounts: List<Account>,
        ): Event()

        data object OnAccountIdSaved: Event()

        sealed class Internal : Event() {
            data class OnAccountSelected(
                val id: Long,
            ) : Internal()
        }
    }
}
