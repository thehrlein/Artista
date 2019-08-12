package com.tobiapplications.artista.utils.network

import com.tobiapplications.artista.model.searchartist.ArtistResponse
import com.tobiapplications.artista.model.topalbums.TopAlbumsResponse
import com.tobiapplications.artista.model.tracks.AlbumTracksResponse
import com.tobiapplications.artista.utils.general.ArtistaUrls
import io.reactivex.Single
import kotlinx.coroutines.Deferred
import retrofit2.Response

class NetworkManager(private val artistaApi: ArtistaAPi) : NetworkManagerDelegate {

    override fun searchArtists(query: String): Deferred<Response<ArtistResponse>> {
        return artistaApi.searchArtists(ArtistaUrls.getSearchArtistUrl(query))
    }

    override fun getTopAlbums(artist: String, albumPage: Int, resultsPerPage: Int): Deferred<Response<TopAlbumsResponse>> {
        return artistaApi.getTopAlbums(ArtistaUrls.getTopAlbumsUrl(artist, albumPage, resultsPerPage))
    }

    override fun getAlbumTracks(artist: String, album: String): Deferred<Response<AlbumTracksResponse>> {
        return artistaApi.getAlbumsTracks(ArtistaUrls.getAlbumTracksUrl(artist, album))
    }
}

