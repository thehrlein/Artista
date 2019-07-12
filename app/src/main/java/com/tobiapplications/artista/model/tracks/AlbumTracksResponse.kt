package com.tobiapplications.artista.model.tracks

import com.google.gson.annotations.SerializedName

data class AlbumTracksResponse(@SerializedName("album") val album: AlbumTracksObject)