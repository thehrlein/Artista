package com.tobiapplications.artista.utils.repository.topalbums

import com.tobiapplications.artista.model.topalbums.TopAlbumRequestModel
import com.tobiapplications.artista.model.topalbums.TopAlbumsResponse
import com.tobiapplications.artista.utils.repository.base.BaseRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopAlbumsRepository @Inject constructor(localSource: TopAlbumsLocalSource,
                                              networkSource: TopAlbumsNetworkSource) : BaseRepository<TopAlbumRequestModel, TopAlbumsResponse>(localSource, networkSource)
