package com.tobiapplications.artista.utils.persistence.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tobiapplications.artista.model.AlbumEntry

@Dao
interface AlbumDao  {

    @Query("SELECT * from album_table ORDER BY timeStamp ASC")
    fun getAllAlbums() : LiveData<List<AlbumEntry>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(albumEntry: AlbumEntry)

    @Update
    fun update(albumEntry: AlbumEntry)

    @Query("DELETE from album_table")
    fun deleteAll()

    @Delete
    fun delete(albumEntry: AlbumEntry)
}
