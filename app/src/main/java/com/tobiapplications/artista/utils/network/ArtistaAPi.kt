package com.tobiapplications.artista.utils.network

import com.tobiapplications.artista.model.searchartist.ArtistResponse
import com.tobiapplications.artista.model.topalbums.TopAlbumsResponse
import com.tobiapplications.artista.model.tracks.AlbumTracksResponse
import io.reactivex.Single
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ArtistaAPi {

    @GET
    suspend fun searchArtists(@Url url: String) : Response<ArtistResponse>

    @GET
    suspend fun getTopAlbums(@Url url: String) : Response<TopAlbumsResponse>

    @GET
    suspend fun getAlbumsTracks(@Url url: String) : Response<AlbumTracksResponse>
}

