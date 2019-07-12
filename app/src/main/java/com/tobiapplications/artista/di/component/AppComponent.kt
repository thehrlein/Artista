package com.tobiapplications.artista.di.component

import android.app.Application
import com.tobiapplications.artista.ArtistaApplication
import com.tobiapplications.artista.di.module.ActivityBuilder
import com.tobiapplications.artista.di.module.NetworkModule
import com.tobiapplications.artista.di.module.PersistenceModule
import com.tobiapplications.artista.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuilder::class,
    NetworkModule::class,
    PersistenceModule::class,
    ViewModelModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: ArtistaApplication)
}
