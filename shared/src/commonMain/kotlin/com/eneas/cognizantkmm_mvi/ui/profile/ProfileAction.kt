package com.eneas.cognizantkmm_mvi.ui.profile

import com.eneas.cognizantkmm_mvi.redux.Action

sealed class ProfileAction : Action {
    object ProfileFailed : ProfileAction()
    object ProfileStarted : ProfileAction()
    data class ProfileLoaded(val welcomeMessage: String) : ProfileAction()
}
