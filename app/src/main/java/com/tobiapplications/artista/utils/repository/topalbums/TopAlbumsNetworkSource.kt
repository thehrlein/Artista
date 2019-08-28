package com.tobiapplications.artista.utils.repository.topalbums

import com.tobiapplications.artista.model.topalbums.TopAlbumRequestModel
import com.tobiapplications.artista.model.topalbums.TopAlbumsResponse
import com.tobiapplications.artista.utils.network.NetworkManagerDelegate
import com.tobiapplications.artista.utils.repository.base.NetworkSourceDelegate
import kotlinx.coroutines.Deferred
import retrofit2.Response

class TopAlbumsNetworkSource constructor(private val networkManager: NetworkManagerDelegate) : NetworkSourceDelegate<TopAlbumRequestModel, TopAlbumsResponse> {

    override suspend fun requestData(input: TopAlbumRequestModel): Deferred<Response<TopAlbumsResponse>> {
        return networkManager.getTopAlbums(input.artist, input.albumPage, input.resultsPerPage)
    }
}