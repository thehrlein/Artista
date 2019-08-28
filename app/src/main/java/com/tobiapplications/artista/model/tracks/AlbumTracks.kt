package com.tobiapplications.artista.model.tracks

import com.google.gson.annotations.SerializedName

data class AlbumTracks(@SerializedName("track") val tracks: List<Track>?)