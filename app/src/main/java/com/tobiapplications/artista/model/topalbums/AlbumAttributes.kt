package com.tobiapplications.artista.model.topalbums

import com.google.gson.annotations.SerializedName

data class AlbumAttributes(@SerializedName("page") val page: Int = 0,
                           @SerializedName("totalPages") val totalPages: Int = 0)