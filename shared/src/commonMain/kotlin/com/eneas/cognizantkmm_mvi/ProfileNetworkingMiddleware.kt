package com.eneas.cognizantkmm_mvi

import com.eneas.cognizantkmm_mvi.redux.Middleware
import com.eneas.cognizantkmm_mvi.redux.Store
import com.eneas.cognizantkmm_mvi.ui.profile.ProfileAction
import com.eneas.cognizantkmm_mvi.ui.profile.ProfileViewState

class ProfileNetworkingMiddleware(
    private val repository: ProdRepository,
) : Middleware<ProfileViewState, ProfileAction> {

    override suspend fun process(
        action: ProfileAction,
        currentState: ProfileViewState,
        store: Store<ProfileViewState, ProfileAction>,
    ) {
        when (action) {
            is ProfileAction.ProfileStarted -> {
                getProfile(store)
            }
        }
    }

    private suspend fun getProfile(
        store: Store<ProfileViewState, ProfileAction>,
    ) {
        repository.profile().foldSuspend({
            store.dispatch(ProfileAction.ProfileFailed)
        },{
            store.dispatch(ProfileAction.ProfileLoaded(it))
        })
    }
}