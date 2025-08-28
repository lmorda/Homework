package com.lmorda.homework.ui.selectAccount

import com.lmorda.homework.domain.usecase.SelectAccountUseCase
import com.lmorda.homework.ui.MviViewModel
import com.lmorda.homework.ui.selectAccount.SelectAccountContract.Event
import com.lmorda.homework.ui.selectAccount.SelectAccountContract.State
import com.lmorda.homework.ui.selectAccount.SelectAccountContract.State.Initial
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectAccountViewModel @Inject constructor(
    private val selectAccountUseCase: SelectAccountUseCase,
) : MviViewModel<State, Event>(Initial) {

    override fun reduce(state: State, event: Event): State = when (event) {
        Event.OnAccountSelected -> Initial
        is Event.Internal.OnAccountSelected -> Initial
    }
}