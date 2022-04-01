package com.eneas.cognizantkmm_mvi.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual class Executor {
    actual val main: CoroutineDispatcher = Dispatchers.Main
    actual val bg: CoroutineDispatcher = Dispatchers.IO
}