package com.lmorda.homework.ui.selectAccount

import androidx.lifecycle.viewModelScope
import com.lmorda.homework.domain.model.Account
import com.lmorda.homework.domain.usecase.GetAccountsUseCase
import com.lmorda.homework.domain.usecase.SelectAccountUseCase
import com.lmorda.homework.ui.MviViewModel
import com.lmorda.homework.ui.selectAccount.SelectAccountContract.Event
import com.lmorda.homework.ui.selectAccount.SelectAccountContract.Event.Internal.OnAccountSelected
import com.lmorda.homework.ui.selectAccount.SelectAccountContract.Event.OnAccountIdSaved
import com.lmorda.homework.ui.selectAccount.SelectAccountContract.Event.OnAccountsError
import com.lmorda.homework.ui.selectAccount.SelectAccountContract.Event.OnAccountsLoaded
import com.lmorda.homework.ui.selectAccount.SelectAccountContract.State
import com.lmorda.homework.ui.selectAccount.SelectAccountContract.State.AccountSelected
import com.lmorda.homework.ui.selectAccount.SelectAccountContract.State.AccountLoadError
import com.lmorda.homework.ui.selectAccount.SelectAccountContract.State.AccountsLoaded
import com.lmorda.homework.ui.selectAccount.SelectAccountContract.State.Initial
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectAccountViewModel @Inject constructor(
    private val getAccountsUseCase: GetAccountsUseCase,
    private val selectAccountUseCase: SelectAccountUseCase,
) : MviViewModel<State, Event>(Initial) {

    init {
        getAccounts()
    }

    override fun reduce(state: State, event: Event): State = when (event) {
        is OnAccountsLoaded -> {
            val accounts = event.accounts
            if (accounts.size == 1) {
                selectAccount(accounts[0])
                state
            } else {
                AccountsLoaded(accounts = accounts)
            }
        }
        is OnAccountSelected -> {
            selectAccount(event.account)
            state
        }
        is OnAccountIdSaved -> {
            AccountSelected
        }
        is OnAccountsError -> AccountLoadError
    }

    private fun getAccounts() {
        viewModelScope.launch {
            try {
                val accounts = getAccountsUseCase()
                push (OnAccountsLoaded(accounts = accounts))
            } catch (ex: Exception) {
                push (OnAccountsError)
            }
        }
    }

    private fun selectAccount(account: Account) {
        viewModelScope.launch {
            try {
                selectAccountUseCase(id = account.id, account.token)
                push (OnAccountIdSaved)
            } catch (ex: Exception) {
                push (OnAccountsError)
            }
        }
    }
}
