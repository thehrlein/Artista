package com.tobiapplications.artista.domain

import com.tobiapplications.artista.model.searchartist.ArtistResponse
import com.tobiapplications.artista.utils.mvvm.BaseSuspendRequestUseCase
import com.tobiapplications.artista.utils.network.NetworkManagerDelegate
import kotlinx.coroutines.Deferred
import retrofit2.Response

class SearchArtistsUseCase constructor(private val networkManager: NetworkManagerDelegate) : BaseSuspendRequestUseCase<String, ArtistResponse>() {

    override suspend fun getData(input: String): Deferred<Response<ArtistResponse>> {
        return networkManager.searchArtists(input)
    }
}