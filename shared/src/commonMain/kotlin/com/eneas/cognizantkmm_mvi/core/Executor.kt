package com.eneas.cognizantkmm_mvi.core

import kotlinx.coroutines.CoroutineDispatcher

expect class Executor {
    val main: CoroutineDispatcher
    val bg: CoroutineDispatcher
}