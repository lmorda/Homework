package com.lmorda.homework.ui.explore

import androidx.lifecycle.viewModelScope
import com.lmorda.homework.data.api.EXPLORE_FILTER_DEBOUNCE_MILLIS
import com.lmorda.homework.domain.DataRepository
import com.lmorda.homework.domain.filters.VehicleFilter
import com.lmorda.homework.domain.filters.VehicleSort
import com.lmorda.homework.domain.model.Vehicle
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
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val dataRepository: DataRepository,
) : MviViewModel<State, Event>(
    initialState = State.Initial,
) {

    private var searchJob: Job? = null

    init {
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
                val vehiclePage = dataRepository.getVehicles(
                    startCursor = nextCursor,
                    sort = VehicleSort.NAME_ASCENDING,
                    filter = nameLike?.takeIf { it.isNotBlank() }?.let {
                        VehicleFilter.NameLike(it)
                    },
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
                val vehiclePage = dataRepository.getVehicles(
                    startCursor = null,
                    sort = VehicleSort.NAME_ASCENDING,
                    filter = nameLike?.takeIf { it.isNotBlank() }?.let {
                        VehicleFilter.NameLike(it)
                    },
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
}
