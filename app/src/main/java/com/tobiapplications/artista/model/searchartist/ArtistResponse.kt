package com.tobiapplications.artista.model.searchartist

import com.google.gson.annotations.SerializedName

data class ArtistResponse(@SerializedName("results") val results: ArtistResult)