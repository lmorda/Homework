package com.lmorda.homework

import androidx.lifecycle.viewModelScope
import com.lmorda.homework.MainContract.Event
import com.lmorda.homework.MainContract.Event.OnCheckNavigation
import com.lmorda.homework.MainContract.State
import com.lmorda.homework.MainContract.State.CheckedInitialNavigation
import com.lmorda.homework.data.credentials.SHARED_PREF_ACCOUNT_ID
import com.lmorda.homework.domain.credentials.TokenDataStore
import com.lmorda.homework.domain.sharedPrefs.HomeworkSharedPrefs
import com.lmorda.homework.ui.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStore: TokenDataStore,
    private val sharedPrefs: HomeworkSharedPrefs,
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
            if (dataStore.getToken() != null && sharedPrefs.getLong(SHARED_PREF_ACCOUNT_ID) != null) {
                push(OnCheckNavigation(route = routeExplore))
            } else if (dataStore.getToken() != null) {
                push(OnCheckNavigation(route = routeSelectAccount))
            } else {
                push(OnCheckNavigation(route = null))
            }
        }
    }
}