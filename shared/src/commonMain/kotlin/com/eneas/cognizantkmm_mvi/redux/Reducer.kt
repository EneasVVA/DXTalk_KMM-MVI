package com.eneas.cognizantkmm_mvi.redux

interface Reducer<S: State, A: Action> {
    fun reduce(currentState: S, action: A): S
}
