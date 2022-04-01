package com.eneas.cognizantkmm_mvi.redux

interface Middleware<S: State, A: Action> {
    suspend fun process(
        action: A,
        currentState: S,
        store: Store<S, A>,
    )
}
