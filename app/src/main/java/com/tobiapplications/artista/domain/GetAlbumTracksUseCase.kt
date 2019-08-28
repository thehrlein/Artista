package com.tobiapplications.artista.domain

import com.tobiapplications.artista.model.tracks.AlbumTracksRequestModel
import com.tobiapplications.artista.model.tracks.AlbumTracksResponse
import com.tobiapplications.artista.utils.mvvm.BaseSuspendRequestUseCase
import com.tobiapplications.artista.utils.network.NetworkManagerDelegate
import kotlinx.coroutines.Deferred
import retrofit2.Response

class GetAlbumTracksUseCase constructor(private val networkManager: NetworkManagerDelegate) : BaseSuspendRequestUseCase<AlbumTracksRequestModel, AlbumTracksResponse>(){

    override suspend fun getData(input: AlbumTracksRequestModel): Deferred<Response<AlbumTracksResponse>> {
        return networkManager.getAlbumTracks(input.artist, input.album)
    }
}