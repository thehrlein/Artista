package com.tobiapplications.artista.utils.network

import com.tobiapplications.artista.model.searchartist.ArtistResponse
import com.tobiapplications.artista.model.topalbums.TopAlbumsResponse
import com.tobiapplications.artista.model.tracks.AlbumTracksResponse
import io.reactivex.Single
import kotlinx.coroutines.Deferred
import retrofit2.Response

interface NetworkManagerDelegate {

    fun searchArtists(query: String) : Deferred<Response<ArtistResponse>>

    fun getTopAlbums(artist: String, albumPage: Int, resultsPerPage: Int) : Deferred<Response<TopAlbumsResponse>>

    fun getAlbumTracks(artist: String, album: String) : Deferred<Response<AlbumTracksResponse>>
}