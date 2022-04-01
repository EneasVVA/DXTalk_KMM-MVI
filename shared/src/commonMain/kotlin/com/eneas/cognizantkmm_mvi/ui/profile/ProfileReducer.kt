package com.eneas.cognizantkmm_mvi.ui.profile

import com.eneas.cognizantkmm_mvi.redux.Reducer
import com.eneas.cognizantkmm_mvi.ui.profile.ProfileAction.ProfileStarted

class ProfileReducer : Reducer<ProfileViewState, ProfileAction> {
    override fun reduce(currentState: ProfileViewState, action: ProfileAction): ProfileViewState {
        return when(action) {
            is ProfileAction.ProfileLoaded -> profileAfterLoaded(currentState, action)
            else -> currentState
        }
    }

    private fun profileAfterLoaded(
        currentState: ProfileViewState,
        action: ProfileAction.ProfileLoaded
    ): ProfileViewState = currentState.copy(welcomeMessage = action.welcomeMessage)
}
