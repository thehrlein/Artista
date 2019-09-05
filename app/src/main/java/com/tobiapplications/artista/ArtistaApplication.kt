package com.tobiapplications.artista

import android.app.Application
import com.tobiapplications.artista.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class ArtistaApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@ArtistaApplication)
            modules(listOf(
                appModule,
                networkModule,
                persistenceModule,
                useCaseModule,
                viewModelModule
            ))
        }
    }
}