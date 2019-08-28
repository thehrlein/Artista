package com.tobiapplications.artista.di

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.tobiapplications.artista.utils.general.SearchQueryStore
import com.tobiapplications.artista.utils.persistence.SharedPreferencesManager
import com.tobiapplications.artista.utils.persistence.room.AlbumDao
import com.tobiapplications.artista.utils.persistence.room.AlbumDatabase
import com.tobiapplications.artista.utils.persistence.room.AlbumRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.multiton
import org.kodein.di.generic.singleton

val persinstenceModule = Kodein.Module("persistenceModule") {

    bind<AlbumDao>() with multiton { application: Application -> AlbumDatabase.getDatabase(application.applicationContext).albumDao() }
    bind<AlbumRepository>() with singleton { AlbumRepository(instance()) }
    bind<SharedPreferences>() with multiton { context: Application -> PreferenceManager.getDefaultSharedPreferences(context) }
    bind<SharedPreferencesManager>() with multiton { sharedPreferences: SharedPreferences -> SharedPreferencesManager(sharedPreferences) }
    bind<SearchQueryStore>() with multiton { sharedPrefManager: SharedPreferencesManager -> SearchQueryStore(sharedPrefManager) }
}
