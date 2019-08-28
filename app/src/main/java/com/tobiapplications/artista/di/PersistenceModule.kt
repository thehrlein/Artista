package com.tobiapplications.artista.di

import android.app.Application
import com.tobiapplications.artista.utils.general.SearchQueryStore
import com.tobiapplications.artista.utils.persistence.SharedPreferencesManager
import com.tobiapplications.artista.utils.persistence.room.AlbumDatabase
import com.tobiapplications.artista.utils.persistence.room.AlbumRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val persistenceModule = Kodein.Module("persistenceModule") {

    bind() from singleton { AlbumDatabase.getDatabase(instance<Application>().applicationContext).albumDao() }
    bind() from singleton { AlbumRepository(instance()) }
    bind() from singleton { SharedPreferencesManager(instance()) }
    bind() from singleton { SearchQueryStore(instance()) }
}
