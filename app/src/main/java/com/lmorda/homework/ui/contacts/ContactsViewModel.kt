package com.lmorda.homework.ui.contacts

import androidx.lifecycle.viewModelScope
import com.lmorda.homework.domain.model.Contact
import com.lmorda.homework.domain.model.mockContactsDomainData
import com.lmorda.homework.ui.MviViewModel
import com.lmorda.homework.ui.contacts.ContactsContract.Event
import com.lmorda.homework.ui.contacts.ContactsContract.Event.Internal.OnLoaded
import com.lmorda.homework.ui.contacts.ContactsContract.Event.OnLoadNextPage
import com.lmorda.homework.ui.contacts.ContactsContract.Event.OnRefresh
import com.lmorda.homework.ui.contacts.ContactsContract.State
import com.lmorda.homework.ui.contacts.ContactsContract.State.Initial
import com.lmorda.homework.ui.contacts.ContactsContract.State.Loaded
import com.lmorda.homework.ui.contacts.ContactsContract.State.LoadingPage
import com.lmorda.homework.ui.contacts.ContactsContract.State.LoadingRefresh
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor() : MviViewModel<State, Event>(
    initialState = Initial,
) {

    init {
        getFirstPage()
    }

    override fun reduce(state: State, event: Event): State = when (event) {
        is OnLoaded -> Loaded(
            contacts = event.contacts,
            nextCursor = event.nextCursor,
        )
        OnLoadNextPage -> {
            if (state is Loaded && state.nextCursor != null) {
                getContactsPage(
                    currentContacts = state.contacts,
                    nextCursor = state.nextCursor,
                )
                LoadingPage(
                    contacts = state.contacts,
                )
            } else state
        }
        OnRefresh -> {
            getFirstPage()
            LoadingRefresh
        }
    }

    private fun getFirstPage() {
        getContactsPage(
            currentContacts = null,
            nextCursor = null,
        )
    }

    private fun getContactsPage(
        currentContacts: List<Contact>?,
        nextCursor: String?,
    ) {
        viewModelScope.launch {
            val contactPage = when {
                nextCursor != null -> mockContactsDomainData[0]
                else -> mockContactsDomainData[1]
            }
            val contacts = currentContacts ?: emptyList()
            val newContacts = contacts + contactPage.contacts
            delay(500)
            push(
                OnLoaded(
                    contacts = newContacts,
                    nextCursor = contactPage.nextCursor,
                )
            )
        }
    }
}
