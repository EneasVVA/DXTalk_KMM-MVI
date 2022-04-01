package com.eneas.cognizantkmm_mvi.android

import android.app.Application
import com.eneas.cognizantkmm_mvi.di.KoinModule
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Napier.base(DebugAntilog())
        startKoin {
            androidContext(this@MyApplication)
            KoinModule.init()
        }
    }
}