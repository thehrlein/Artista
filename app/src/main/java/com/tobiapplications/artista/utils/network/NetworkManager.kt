package com.tobiapplications.artista.utils.network

import com.tobiapplications.artista.model.searchartist.ArtistResponse
import com.tobiapplications.artista.model.topalbums.TopAlbumsResponse
import com.tobiapplications.artista.model.tracks.AlbumTracksResponse
import com.tobiapplications.artista.utils.general.ArtistaUrls
import io.reactivex.Single
import kotlinx.coroutines.Deferred
import retrofit2.Response

class NetworkManager(private val artistaApi: ArtistaAPi) : NetworkManagerDelegate {

    override suspend fun searchArtists(query: String): Response<ArtistResponse> {
        return artistaApi.searchArtists(ArtistaUrls.getSearchArtistUrl(query))
    }

    override suspend fun getTopAlbums(artist: String, albumPage: Int, resultsPerPage: Int): Response<TopAlbumsResponse> {
        return artistaApi.getTopAlbums(ArtistaUrls.getTopAlbumsUrl(artist, albumPage, resultsPerPage))
    }

    override suspend fun getAlbumTracks(artist: String, album: String): Response<AlbumTracksResponse> {
        return artistaApi.getAlbumsTracks(ArtistaUrls.getAlbumTracksUrl(artist, album))
    }
}

