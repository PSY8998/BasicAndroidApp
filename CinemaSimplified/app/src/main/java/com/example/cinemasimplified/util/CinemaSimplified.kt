package com.example.cinemasimplified.util

import android.app.Application
import com.example.cinemasimplified.data.network.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class CinemaSimplified: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@CinemaSimplified)
            modules(
                networkModule

            )
        }
    }
}