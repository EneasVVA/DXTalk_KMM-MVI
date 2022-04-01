package com.eneas.cognizantkmm_mvi

import com.eneas.cognizantkmm_mvi.redux.Middleware
import com.eneas.cognizantkmm_mvi.redux.Store
import com.eneas.cognizantkmm_mvi.ui.login.LoginAction
import com.eneas.cognizantkmm_mvi.ui.login.LoginViewState

class LoginNetworkingMiddleware(
    private val repository: ProdRepository,
) : Middleware<LoginViewState, LoginAction> {

    override suspend fun process(
        action: LoginAction,
        currentState: LoginViewState,
        store: Store<LoginViewState, LoginAction>,
    ) {
        when (action) {
            is LoginAction.SignInButtonClicked -> {
                if (currentState.email.isEmpty()) {
                    store.dispatch(LoginAction.InvalidEmailSubmitted)
                    return
                }

                loginUser(store, currentState)
            }
        }
    }

    private suspend fun loginUser(
        store: Store<LoginViewState, LoginAction>,
        currentState: LoginViewState
    ) {
        store.dispatch(LoginAction.LoginStarted)

        repository.login(
            email = currentState.email,
            password = currentState.password,
        ).foldSuspend({
            store.dispatch(LoginAction.LoginFailed(it.message))
        },{
            store.dispatch(LoginAction.LoginCompleted)
        })
    }
}