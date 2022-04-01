package com.eneas.cognizantkmm_mvi.ui

import com.eneas.cognizantkmm_mvi.redux.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

expect abstract class MyViewModel<V : State>() {

    abstract val viewState: StateFlow<V>

    protected val viewModelScope: CoroutineScope

    open fun attach()

    open fun detach()
}