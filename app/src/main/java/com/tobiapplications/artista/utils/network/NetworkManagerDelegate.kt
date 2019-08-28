package com.tobiapplications.artista.utils.network

import com.tobiapplications.artista.model.searchartist.ArtistResponse
import com.tobiapplications.artista.model.topalbums.TopAlbumsResponse
import com.tobiapplications.artista.model.tracks.AlbumTracksResponse
import io.reactivex.Single
import kotlinx.coroutines.Deferred
import retrofit2.Response

interface NetworkManagerDelegate {

    suspend fun searchArtists(query: String) : Response<ArtistResponse>

    suspend fun getTopAlbums(artist: String, albumPage: Int, resultsPerPage: Int) : Response<TopAlbumsResponse>

    suspend fun getAlbumTracks(artist: String, album: String) : Response<AlbumTracksResponse>
}