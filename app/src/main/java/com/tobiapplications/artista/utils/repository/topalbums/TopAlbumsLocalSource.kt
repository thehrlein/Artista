package com.tobiapplications.artista.utils.repository.topalbums

import com.tobiapplications.artista.model.topalbums.TopAlbumRequestModel
import com.tobiapplications.artista.model.topalbums.TopAlbumsResponse
import com.tobiapplications.artista.utils.repository.base.BaseLocalSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopAlbumsLocalSource @Inject constructor() : BaseLocalSource<TopAlbumRequestModel, TopAlbumsResponse>()