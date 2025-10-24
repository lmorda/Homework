package com.lmorda.homework.ui.login

import androidx.lifecycle.viewModelScope
import com.lmorda.homework.domain.usecase.LoginUseCase
import com.lmorda.homework.ui.MviViewModel
import com.lmorda.homework.ui.login.LoginContract.Event
import com.lmorda.homework.ui.login.LoginContract.Event.Internal.OnLogin
import com.lmorda.homework.ui.login.LoginContract.Event.OnLoggedIn
import com.lmorda.homework.ui.login.LoginContract.Event.OnLoginError
import com.lmorda.homework.ui.login.LoginContract.State
import com.lmorda.homework.ui.login.LoginContract.State.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : MviViewModel<State, Event>(
    initialState = Initial,
) {

    override fun reduce(state: State, event: Event): State = when (event) {
        is OnLogin -> {
            login(username = event.username, password = event.password)
            LoggingIn
        }
        OnLoggedIn -> LoggedIn
        OnLoginError -> LoginError
    }

    private fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                loginUseCase.invoke(username = username, password = password)
                push(OnLoggedIn)
            } catch (e: Exception) {
                push(OnLoginError)
            }
        }
    }
}
