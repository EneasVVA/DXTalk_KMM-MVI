package com.eneas.cognizantkmm_mvi.di

import com.eneas.cognizantkmm_mvi.ProdRepository
import com.eneas.cognizantkmm_mvi.ProdService
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

object KoinModule {
    fun init() = init
    fun initiOS() = startKoin { init }
}

private val init by lazy {
    loadKoinModules(
        listOf(
            platformModule,
            prodService
        )
    )
}

val prodService = module {
    single<ProdRepository> {
        ProdService()
    }
}

expect val platformModule: Module