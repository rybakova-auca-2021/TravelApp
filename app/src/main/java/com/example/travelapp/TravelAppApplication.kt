package com.example.travelapp

import android.app.Application
import com.chaos.view.BuildConfig
import com.example.travelapp.presentation.di.injectFeature
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level

class TravelAppApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        GlobalContext.startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@TravelAppApplication)
            injectFeature()
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}