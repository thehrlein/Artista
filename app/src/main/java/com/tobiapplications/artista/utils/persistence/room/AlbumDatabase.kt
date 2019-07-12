package com.tobiapplications.artista.utils.persistence.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tobiapplications.artista.model.AlbumEntry

@Database(entities = [AlbumEntry::class], version = 1, exportSchema = false)
@TypeConverters(TrackListConverter::class)
abstract class AlbumDatabase : RoomDatabase() {

    abstract fun albumDao() : AlbumDao

    companion object {
        @Volatile
        private var INSTANCE : AlbumDatabase? = null

        fun getDatabase(context: Context) : AlbumDatabase {
            return INSTANCE ?: synchronized(this) {
                val tempInstance = Room.databaseBuilder(context.applicationContext,
                    AlbumDatabase::class.java,
                    "Album_database")
                    .build()
                INSTANCE = tempInstance
                return tempInstance
            }
        }
    }
}