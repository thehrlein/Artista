package com.tobiapplications.artista.model.tracks

import com.google.gson.annotations.SerializedName
import com.tobiapplications.artista.model.topalbums.AlbumImage

data class AlbumTracksObject(@SerializedName("name") val name: String,
                             @SerializedName("artist") val artist: String,
                             @SerializedName("url") val url: String,
                             @SerializedName("playcount") val playCount: Long,
                             @SerializedName("image") val images: List<AlbumImage>,
                             @SerializedName("tracks") val tracks: AlbumTracks)