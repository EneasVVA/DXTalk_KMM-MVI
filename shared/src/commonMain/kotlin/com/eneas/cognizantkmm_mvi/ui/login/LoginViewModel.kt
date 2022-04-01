package com.eneas.cognizantkmm_mvi.ui.login

import com.eneas.cognizantkmm_mvi.LoggingMiddleware
import com.eneas.cognizantkmm_mvi.LoginNetworkingMiddleware
import com.eneas.cognizantkmm_mvi.ProdRepository
import com.eneas.cognizantkmm_mvi.redux.Store
import com.eneas.cognizantkmm_mvi.ui.MyViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginViewModel : MyViewModel<LoginViewState>(), KoinComponent {
    private val repository: ProdRepository by inject()

    private val store = Store(
        initialState = LoginViewState(),
        reducer = LoginReducer(),
        middlewares = listOf(
            LoggingMiddleware(),
            LoginNetworkingMiddleware(
                repository = repository
            ),
        )
    )

    override val viewState: StateFlow<LoginViewState> = store.state

    fun emailChanged(newEmail: String) {
        val action = LoginAction.EmailChanged(newEmail)

        viewModelScope.launch {
            store.dispatch(action)
        }
    }

    fun passwordChanged(newPassword: String) {
        val action = LoginAction.PasswordChanged(newPassword)

        viewModelScope.launch {
            store.dispatch(action)
        }
    }

    fun signInButtonClicked() {
        val action = LoginAction.SignInButtonClicked

        viewModelScope.launch {
            store.dispatch(action)
        }
    }

    fun loginUncompleted() {
        val action = LoginAction.LoginUncompleted

        viewModelScope.launch {
            store.dispatch(action)
        }
    }

    override fun attach() {
        super.attach()
        loginUncompleted()
    }

}