package com.tobiapplications.artista.domain

import com.tobiapplications.artista.model.topalbums.TopAlbumRequestModel
import com.tobiapplications.artista.model.topalbums.TopAlbumsResponse
import com.tobiapplications.artista.utils.mvvm.BaseRepositoryRequestUseCase
import com.tobiapplications.artista.utils.repository.topalbums.TopAlbumsRepository
import javax.inject.Inject

class GetTopAlbumsUseCase @Inject constructor(private val topAlbumsRepository: TopAlbumsRepository) : BaseRepositoryRequestUseCase<TopAlbumRequestModel, TopAlbumsResponse, TopAlbumsResponse>(topAlbumsRepository) {

    override suspend fun execute(parameters: TopAlbumRequestModel) {
        if (parameters.clearLocalCache) {
            topAlbumsRepository.clear()
        }

        super.execute(parameters)
    }

    override fun transformResponse(input: TopAlbumsResponse?): TopAlbumsResponse {
        return input ?: TopAlbumsResponse()
    }
}