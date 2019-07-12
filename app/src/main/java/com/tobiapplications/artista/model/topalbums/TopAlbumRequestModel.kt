package com.tobiapplications.artista.model.topalbums

data class TopAlbumRequestModel(val artist: String,
                                val albumPage: Int,
                                val resultsPerPage: Int,
                                val clearLocalCache: Boolean)