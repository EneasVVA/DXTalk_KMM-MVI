package com.eneas.cognizantkmm_mvi.ui.profile

import com.eneas.cognizantkmm_mvi.redux.State

data class ProfileViewState(
    val welcomeMessage: String = ""
    ) : State