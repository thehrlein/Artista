package com.tobiapplications.artista

import android.app.Application
import com.tobiapplications.artista.di.*
import org.kodein.di.DKodein
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
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
            bind<DKodein>() with singleton { this.dkodein }
            import(appModule)
            import(networkModule)
            import(persistenceModule)
            import(useCaseModule)
            import(viewModelModule)
        }
}