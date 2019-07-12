package com.tobiapplications.artista.model.topalbums

import com.google.gson.annotations.SerializedName

data class AlbumImage(@SerializedName("#text") val url: String,
                      @SerializedName("size") val size: String)
