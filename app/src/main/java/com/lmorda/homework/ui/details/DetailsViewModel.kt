package com.lmorda.homework.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.lmorda.homework.domain.repository.DataRepository
import com.lmorda.homework.ui.MviViewModel
import com.lmorda.homework.ui.details.DetailsContract.Event
import com.lmorda.homework.ui.details.DetailsContract.Event.Internal.OnLoadError
import com.lmorda.homework.ui.details.DetailsContract.Event.Internal.OnLoaded
import com.lmorda.homework.ui.details.DetailsContract.Event.OnLoadDetails
import com.lmorda.homework.ui.details.DetailsContract.State
import com.lmorda.homework.ui.details.DetailsContract.State.LoadError
import com.lmorda.homework.ui.details.DetailsContract.State.Loaded
import com.lmorda.homework.ui.details.DetailsContract.State.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

const val SAVED_ID_KEY = "id"

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val dataRepository: DataRepository,
) : MviViewModel<State, Event>(
    initialState = State.Initial
) {

    private val id: Long? = savedStateHandle[SAVED_ID_KEY]

    override fun reduce(state: State, event: Event): State = when (event) {
        is OnLoadDetails -> {
            id?.let { getVehicle(id = it) }
            Loading
        }

        is OnLoaded -> Loaded(vehicle = event.vehicle)
        is OnLoadError -> LoadError(errorMessage = event.errorMessage)
    }

    private fun getVehicle(id: Long) {
        viewModelScope.launch {
            try {
                val vehicle = dataRepository.getVehicle(id = id)
                push(OnLoaded(vehicle = vehicle))
            } catch (e: Exception) {
                push(OnLoadError(errorMessage = e.message))
            }
        }
    }
}
