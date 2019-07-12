package com.tobiapplications.artista.model.searchartist

import com.google.gson.annotations.SerializedName

data class ArtistMatches(@SerializedName("artist") val artists: List<Artist>)