package com.tobiapplications.artista.domain

import com.tobiapplications.artista.model.topalbums.TopAlbumRequestModel
import com.tobiapplications.artista.model.topalbums.TopAlbumsResponse
import com.tobiapplications.artista.utils.mvvm.BaseRequestUseCase
import com.tobiapplications.artista.utils.network.NetworkManagerDelegate
import retrofit2.Response


class GetTopAlbumsUseCase constructor(private val networkManager: NetworkManagerDelegate) : BaseRequestUseCase<TopAlbumRequestModel, TopAlbumsResponse>() {

    override suspend fun getData(input: TopAlbumRequestModel): Response<TopAlbumsResponse> {
        return networkManager.getTopAlbums(input.artist, input.albumPage, input.resultsPerPage)
    }
}