package com.tobiapplications.artista.utils.repository.topalbums

import com.tobiapplications.artista.model.topalbums.TopAlbumRequestModel
import com.tobiapplications.artista.model.topalbums.TopAlbumsResponse
import com.tobiapplications.artista.utils.network.NetworkManagerDelegate
import com.tobiapplications.artista.utils.repository.base.NetworkSourceDelegate
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopAlbumsNetworkSource @Inject constructor(private val networkManager: NetworkManagerDelegate) : NetworkSourceDelegate<TopAlbumRequestModel, TopAlbumsResponse> {

    override fun requestData(input: TopAlbumRequestModel): Single<Response<TopAlbumsResponse>> {
        return networkManager.getTopAlbums(input.artist, input.albumPage, input.resultsPerPage)
    }
}