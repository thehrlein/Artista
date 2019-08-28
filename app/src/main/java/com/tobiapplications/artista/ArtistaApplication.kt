package com.tobiapplications.artista

import android.app.Application
import com.tobiapplications.artista.di.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import timber.log.Timber

class ArtistaApplication : Application(), KodeinAware {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override val kodein by Kodein.lazy {
            import(androidXModule(this@ArtistaApplication))
            import(appModule)
            import(networkModule)
            import(persinstenceModule)
            import(useCaseModule)
            import(viewModelModule)
        }
}