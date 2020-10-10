package com.tobiapplications.artista.model.tracks

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Track(@SerializedName("name") val name: String,
                 @SerializedName("url") val url: String,
                 @SerializedName("duration") val duration: Int): Serializable