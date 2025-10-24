package com.lmorda.homework

import androidx.lifecycle.viewModelScope
import com.lmorda.homework.MainContract.Event
import com.lmorda.homework.MainContract.Event.OnCheckNavigation
import com.lmorda.homework.MainContract.State
import com.lmorda.homework.MainContract.State.CheckedInitialNavigation
import com.lmorda.homework.domain.credentials.TokenDataStore
import com.lmorda.homework.ui.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStore: TokenDataStore,
): MviViewModel<State, Event>(
    initialState = State.Initial,
) {

    init {
        checkInitialNavigationRoute()
    }

    override fun reduce(state: State, event: Event): State = when (event) {
        is OnCheckNavigation -> CheckedInitialNavigation(
            route = event.route ?: routeLogin,
        )
    }

    private fun checkInitialNavigationRoute() {
        viewModelScope.launch {
            if (dataStore.getOauthToken() != null && dataStore.getAccountToken() != null) {
                push(OnCheckNavigation(route = routeExplore))
            } else if (dataStore.getOauthToken() != null) {
                push(OnCheckNavigation(route = routeSelectAccount))
            } else {
                push(OnCheckNavigation(route = null))
            }
        }
    }
}