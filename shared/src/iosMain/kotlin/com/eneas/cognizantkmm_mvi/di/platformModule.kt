package com.eneas.cognizantkmm_mvi.di

import com.eneas.cognizantkmm_mvi.core.Executor
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single { Executor() }
}

fun debugBuild() {
    Napier.base(DebugAntilog())
}