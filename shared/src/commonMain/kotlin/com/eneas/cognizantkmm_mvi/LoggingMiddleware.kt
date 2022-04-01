package com.eneas.cognizantkmm_mvi

import io.github.aakira.napier.Napier
import com.eneas.cognizantkmm_mvi.redux.Action
import com.eneas.cognizantkmm_mvi.redux.Middleware
import com.eneas.cognizantkmm_mvi.redux.State
import com.eneas.cognizantkmm_mvi.redux.Store

class LoggingMiddleware<S: State, A: Action> : Middleware<S, A> {
    override suspend fun process(action: A, currentState: S, store: Store<S, A>) {
        Napier.v(
            tag = "LoggingMiddleware",
            message = "Processing action: $action; Current state: $currentState"
        )
    }
}