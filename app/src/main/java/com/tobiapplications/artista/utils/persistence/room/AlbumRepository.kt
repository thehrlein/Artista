package com.tobiapplications.artista.utils.persistence.room

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.tobiapplications.artista.model.AlbumEntry
import java.util.*

class AlbumRepository(private val albumDao: AlbumDao) {

    val favoriteAlbums : LiveData<List<AlbumEntry>> = albumDao.getAllAlbums()

    @WorkerThread
    suspend fun insert(albumEntry: AlbumEntry) {
        albumEntry.timeStamp = Date().time
        albumDao.insert(albumEntry)
    }

    fun update(albumEntry: AlbumEntry) {
        albumDao.update(albumEntry)
    }

    @WorkerThread
    fun delete(albumEntry: AlbumEntry) {
        albumDao.delete(albumEntry)
    }
}