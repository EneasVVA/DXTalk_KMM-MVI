package com.eneas.cognizantkmm_mvi.ui.login

import com.eneas.cognizantkmm_mvi.redux.State

data class LoginViewState(
    val email: String = "",
    val password: String = "",
    val showProgressBar: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null,
    val completed: Boolean = false
) : State