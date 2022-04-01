package com.eneas.cognizantkmm_mvi.ui.profile

import com.eneas.cognizantkmm_mvi.LoggingMiddleware
import com.eneas.cognizantkmm_mvi.ProdRepository
import com.eneas.cognizantkmm_mvi.ProfileNetworkingMiddleware
import com.eneas.cognizantkmm_mvi.redux.Store
import com.eneas.cognizantkmm_mvi.ui.MyViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ProfileViewModel : MyViewModel<ProfileViewState>(), KoinComponent {
    private val repository: ProdRepository by inject()

    private val store = Store(
        initialState = ProfileViewState(),
        reducer = ProfileReducer(),
        middlewares = listOf(
            LoggingMiddleware(),
            ProfileNetworkingMiddleware(
                repository = repository
            )
        )
    )

    override val viewState: StateFlow<ProfileViewState> = store.state

    override fun attach() {
        super.attach()
        getProfile()
    }

    fun getProfile() {
        val action = ProfileAction.ProfileStarted

        viewModelScope.launch {
            store.dispatch(action)
        }
    }
}