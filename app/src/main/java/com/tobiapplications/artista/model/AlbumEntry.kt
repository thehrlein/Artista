package com.tobiapplications.artista.model

import androidx.room.Entity
import androidx.room.Ignore
import com.tobiapplications.artista.model.tracks.Track
import com.tobiapplications.artista.utils.general.DisplayableItem
import java.io.Serializable
import java.util.*

@Entity(tableName = "album_table", primaryKeys = ["name", "artist"])
data class AlbumEntry(val name: String,
                      val playCount: Long,
                      val url: String,
                      val artist: String,
                      val image: String,
                      var trackList: List<Track> = emptyList(),
                      var timeStamp: Long = 0L) : DisplayableItem, Serializable {

    @Ignore
    var isFavorite : Boolean = false

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        if (javaClass != other.javaClass) return false
        if (other !is AlbumEntry) return false

        return hashCode() == other.hashCode()
    }

    override fun hashCode(): Int {
        return Objects.hash(name, playCount, url, artist, image)
    }
}
