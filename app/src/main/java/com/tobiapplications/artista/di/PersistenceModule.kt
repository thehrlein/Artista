package com.tobiapplications.artista.di

import android.app.Application
import android.preference.PreferenceManager
import com.tobiapplications.artista.utils.general.SearchQueryStore
import com.tobiapplications.artista.utils.persistence.SharedPreferencesManager
import com.tobiapplications.artista.utils.persistence.room.AlbumDatabase
import com.tobiapplications.artista.utils.persistence.room.AlbumRepository
import org.koin.dsl.module

val persistenceModule = module {

    single { AlbumDatabase.getDatabase(get<Application>().applicationContext).albumDao() }
    single { AlbumRepository(get()) }
    single { PreferenceManager.getDefaultSharedPreferences(get())}
    single { SharedPreferencesManager(get()) }
    single { SearchQueryStore(get()) }
}
