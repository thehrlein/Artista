package com.tobiapplications.artista.domain

import com.tobiapplications.artista.model.tracks.AlbumTracksResponse
import com.tobiapplications.artista.model.tracks.AlbumTracksRequestModel
import com.tobiapplications.artista.utils.mvvm.BaseRequestUseCase
import com.tobiapplications.artista.utils.network.NetworkManagerDelegate
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class GetAlbumTracksUseCase @Inject constructor(private val networkManager: NetworkManagerDelegate) : BaseRequestUseCase<AlbumTracksRequestModel, AlbumTracksResponse>() {

    override fun getData(input: AlbumTracksRequestModel): Single<Response<AlbumTracksResponse>> {
        return networkManager.getAlbumTracks(input.artist, input.album)
    }
}