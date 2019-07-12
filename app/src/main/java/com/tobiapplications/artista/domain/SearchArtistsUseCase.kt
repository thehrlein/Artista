package com.tobiapplications.artista.domain

import com.tobiapplications.artista.model.searchartist.ArtistResponse
import com.tobiapplications.artista.utils.mvvm.BaseRequestUseCase
import com.tobiapplications.artista.utils.network.NetworkManagerDelegate
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class SearchArtistsUseCase @Inject constructor(private val networkManager: NetworkManagerDelegate) : BaseRequestUseCase<String, ArtistResponse>() {

    override fun getData(input: String): Single<Response<ArtistResponse>> {
        return networkManager.searchArtists(input)
    }
}