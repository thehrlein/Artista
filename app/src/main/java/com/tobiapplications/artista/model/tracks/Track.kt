package com.tobiapplications.artista.model.tracks

import com.google.gson.annotations.SerializedName

data class Track(@SerializedName("name") val name: String,
                 @SerializedName("url") val url: String,
                 @SerializedName("duration") val duration: Int)