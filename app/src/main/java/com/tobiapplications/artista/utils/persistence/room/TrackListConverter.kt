package com.tobiapplications.artista.utils.persistence.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.tobiapplications.artista.model.tracks.Track

/**
 *  Created by tobiashehrlein on 2019-07-11
 */
class TrackListConverter  {

    @TypeConverter
    fun toString(tracks: List<Track>) : String {
        return Gson().toJson(tracks)
    }

    @TypeConverter
    fun toTrackList(string: String) : List<Track> {
        return (Gson().fromJson(string, Array<Track>::class.java) as Array<Track>).toList()
    }
}