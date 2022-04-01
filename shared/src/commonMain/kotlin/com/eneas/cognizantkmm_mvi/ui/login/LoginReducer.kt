package com.eneas.cognizantkmm_mvi.ui.login

import com.eneas.cognizantkmm_mvi.redux.Reducer

class LoginReducer : Reducer<LoginViewState, LoginAction> {

    override fun reduce(currentState: LoginViewState, action: LoginAction): LoginViewState {
        return when (action) {
            is LoginAction.EmailChanged -> {
                stateWithNewEmail(currentState, action)
            }
            is LoginAction.PasswordChanged -> {
                stateWithNewPassword(currentState, action)
            }
            LoginAction.LoginStarted -> {
                stateAfterLoginStarted(currentState)
            }
            LoginAction.LoginCompleted -> {
                stateAfterLoginCompleted(currentState)
            }
            LoginAction.LoginUncompleted -> {
                stateAfterLoginUncompleted(currentState)
            }
            is LoginAction.LoginFailed -> {
                stateAfterLoginFailed(currentState)
            }
            LoginAction.InvalidEmailSubmitted -> {
                stateWithInvalidEmailError(currentState)
            }
            else -> currentState
        }
    }

    private fun stateWithInvalidEmailError(currentState: LoginViewState) =
        currentState.copy(
            emailError = "Please enter an email address.",
        )

    private fun stateAfterLoginStarted(currentState: LoginViewState) =
        currentState.copy(
            showProgressBar = true,
        )

    private fun stateAfterLoginCompleted(currentState: LoginViewState) =
        currentState.copy(
            showProgressBar = false,
            completed = true
        )

    private fun stateAfterLoginUncompleted(currentState: LoginViewState) =
        currentState.copy(
            completed = false
        )

    private fun stateAfterLoginFailed(currentState: LoginViewState) =
        currentState.copy(
            showProgressBar = false,
        )

    private fun stateWithNewPassword(
        currentState: LoginViewState,
        action: LoginAction.PasswordChanged
    ) = currentState.copy(
        password = action.newPassword,
    )

    private fun stateWithNewEmail(
        currentState: LoginViewState,
        action: LoginAction.EmailChanged
    ) = currentState.copy(
        email = action.newEmail,
    )
}