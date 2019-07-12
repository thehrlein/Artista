package com.tobiapplications.artista.model.topalbums

import com.google.gson.annotations.SerializedName

data class TopAlbums(@SerializedName("album") val album: List<TopAlbum> = emptyList(),
                     @SerializedName("@attr") val albumAttributes: AlbumAttributes = AlbumAttributes())