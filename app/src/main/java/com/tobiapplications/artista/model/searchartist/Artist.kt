package com.tobiapplications.artista.model.searchartist

import com.google.gson.annotations.SerializedName

data class Artist(@SerializedName("name") val artist: String)