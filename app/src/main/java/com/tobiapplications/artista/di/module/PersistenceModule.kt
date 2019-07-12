package com.tobiapplications.artista.di.module

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.tobiapplications.artista.utils.persistence.SharedPreferencesManager
import com.tobiapplications.artista.utils.persistence.room.AlbumDao
import com.tobiapplications.artista.utils.persistence.room.AlbumDatabase
import com.tobiapplications.artista.utils.persistence.room.AlbumRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PersistenceModule {

    @Singleton
    @Provides
    fun provideAlbumDao(application: Application) : AlbumDao {
        return AlbumDatabase.getDatabase(application.applicationContext).albumDao()
    }

    @Singleton
    @Provides
    fun provideAlbumRepository(albumDao: AlbumDao) : AlbumRepository {
        return AlbumRepository(albumDao)
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(context: Application) : SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Singleton
    @Provides
    fun provideSharedPreferencesManager(sharedPreferences: SharedPreferences) : SharedPreferencesManager {
        return SharedPreferencesManager(sharedPreferences)
    }
}