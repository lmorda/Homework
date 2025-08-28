package com.lmorda.homework.ui.explore

import androidx.lifecycle.viewModelScope
import com.lmorda.homework.data.api.EXPLORE_FILTER_DEBOUNCE_MILLIS
import com.lmorda.homework.domain.DataRepository
import com.lmorda.homework.domain.featureflag.FeatureFlag
import com.lmorda.homework.domain.featureflag.FeatureFlagRepository
import com.lmorda.homework.domain.filters.VehicleFilter
import com.lmorda.homework.domain.filters.VehicleSort
import com.lmorda.homework.domain.model.Vehicle
import com.lmorda.homework.domain.usecase.GetVehiclePageUseCase
import com.lmorda.homework.ui.MviViewModel
import com.lmorda.homework.ui.explore.ExploreContract.Event
import com.lmorda.homework.ui.explore.ExploreContract.Event.Internal.OnLoadError
import com.lmorda.homework.ui.explore.ExploreContract.Event.Internal.OnLoaded
import com.lmorda.homework.ui.explore.ExploreContract.Event.OnLoadNextPage
import com.lmorda.homework.ui.explore.ExploreContract.Event.OnRefresh
import com.lmorda.homework.ui.explore.ExploreContract.Event.OnSearchName
import com.lmorda.homework.ui.explore.ExploreContract.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val featureFlagRepository: FeatureFlagRepository,
    private val getVehiclePageUseCase: GetVehiclePageUseCase,
) : MviViewModel<State, Event>(
    initialState = State.Initial,
) {

    private val _showContacts = MutableStateFlow(false)
    val showContacts: StateFlow<Boolean> = _showContacts.asStateFlow()

    private var searchJob: Job? = null

    init {
        getFeatureFlags()
        getFirstPage()
    }

    override fun reduce(state: State, event: Event): State = when (event) {
        is OnLoadNextPage -> {
            if (state is State.Loaded && state.nextCursor != null) {
                getVehiclePage(
                    currentVehicles = state.vehicles,
                    nextCursor = state.nextCursor,
                    nameLike = state.nameLike,
                )
                State.LoadingPage(
                    vehicles = state.vehicles,
                )
            } else state
        }

        is OnLoaded -> State.Loaded(
            vehicles = event.vehicles,
            nextCursor = event.nextCursor,
            nameLike = event.nameLike,
        )

        is OnLoadError -> State.LoadError(
            errorMessage = event.errorMessage,
        )

        is OnSearchName -> {
            getFilteredFirstPage(nameLike = event.name)
            state
        }

        is OnRefresh -> {
            getFirstPage()
            State.LoadingRefresh
        }
    }

    private fun getFirstPage() {
        getVehiclePage(
            currentVehicles = null,
            nextCursor = null,
            nameLike = null,
        )
    }

    private fun getVehiclePage(
        currentVehicles: List<Vehicle>?,
        nextCursor: String?,
        nameLike: String?,
    ) {
        viewModelScope.launch {
            try {
                val vehiclePage = getVehiclePageUseCase.invoke(
                    startCursor = nextCursor,
                    sort = VehicleSort.NAME_ASCENDING,
                    nameLike = nameLike,
                )
                val vehicles = currentVehicles ?: emptyList()
                val newVehicles = vehicles + vehiclePage.records
                push(
                    OnLoaded(
                        vehicles = newVehicles,
                        nextCursor = vehiclePage.nextCursor,
                        nameLike = nameLike,
                    )
                )
            } catch (e: Exception) {
                push(OnLoadError(errorMessage = e.message))
            }
        }
    }

    private fun getFilteredFirstPage(nameLike: String?) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            if (!nameLike.isNullOrBlank()) {
                delay(EXPLORE_FILTER_DEBOUNCE_MILLIS)
            }
            try {
                val vehiclePage = getVehiclePageUseCase.invoke(
                    startCursor = null,
                    sort = VehicleSort.NAME_ASCENDING,
                    nameLike = nameLike,
                )
                push(
                    OnLoaded(
                        vehicles = vehiclePage.records,
                        nextCursor = vehiclePage.nextCursor,
                        nameLike = nameLike,
                    )
                )
            } catch (e: Exception) {
                if (e is CancellationException) throw e
                push(OnLoadError(errorMessage = e.message))
            }
        }
    }

    private fun getFeatureFlags() {
        viewModelScope.launch {
            featureFlagRepository.isFeatureEnabled(FeatureFlag.ShowContacts).collectLatest { showContacts ->
                _showContacts.value = showContacts
            }
        }
    }
}
