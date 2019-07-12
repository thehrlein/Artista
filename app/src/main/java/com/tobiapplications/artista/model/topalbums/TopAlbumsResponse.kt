package com.tobiapplications.artista.model.topalbums

import com.google.gson.annotations.SerializedName

data class TopAlbumsResponse(@SerializedName("topalbums") val topAlbums: TopAlbums = TopAlbums())