package com.tobiapplications.artista.model.searchartist

import com.google.gson.annotations.SerializedName

data class ArtistResult(@SerializedName("artistmatches") val artistMatches: ArtistMatches)