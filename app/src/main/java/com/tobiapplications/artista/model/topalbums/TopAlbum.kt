package com.tobiapplications.artista.model.topalbums

import com.google.gson.annotations.SerializedName
import com.tobiapplications.artista.model.searchartist.Artist

data class TopAlbum(@SerializedName("name") val name: String,
                    @SerializedName("artist") val artist: Artist,
                    @SerializedName("url") val url: String,
                    @SerializedName("playcount") val playCount: Long,
                    @SerializedName("image") val images: List<AlbumImage>)