package com.lmorda.homework.ui.contacts

import com.lmorda.homework.domain.model.Contact

interface ContactsContract {

    sealed class State {

        data object Initial : State()

        data class Loaded(
            val contacts: List<Contact>,
            val nextCursor: String?,
        ) : State()

        data class LoadingPage(
            val contacts: List<Contact>,
        ) : State()

        data object LoadingRefresh : State()

    }

    sealed class Event {

        data object OnRefresh : Event()
        data object OnLoadNextPage : Event()

        sealed class Internal : Event() {
            data class OnLoaded(
                val contacts: List<Contact>,
                val nextCursor: String?,
            ) : Internal()
        }
    }
}
