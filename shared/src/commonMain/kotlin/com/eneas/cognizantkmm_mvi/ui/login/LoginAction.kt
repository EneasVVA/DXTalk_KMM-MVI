package com.eneas.cognizantkmm_mvi.ui.login

import com.eneas.cognizantkmm_mvi.redux.Action

sealed class LoginAction : Action {
    data class EmailChanged(val newEmail: String) : LoginAction()
    data class PasswordChanged(val newPassword: String) : LoginAction()
    object SignInButtonClicked : LoginAction()
    object LoginStarted : LoginAction()
    object LoginCompleted : LoginAction()
    object LoginUncompleted : LoginAction()
    data class LoginFailed(val error: String?) : LoginAction()
    object InvalidEmailSubmitted : LoginAction()
}
