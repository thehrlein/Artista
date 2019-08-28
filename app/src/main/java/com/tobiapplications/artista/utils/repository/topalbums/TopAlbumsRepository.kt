package com.tobiapplications.artista.utils.repository.topalbums

import com.tobiapplications.artista.model.topalbums.TopAlbumRequestModel
import com.tobiapplications.artista.model.topalbums.TopAlbumsResponse
import com.tobiapplications.artista.utils.repository.base.BaseRepository

class TopAlbumsRepository constructor(localSource: TopAlbumsLocalSource,
                                       networkSource: TopAlbumsNetworkSource) : BaseRepository<TopAlbumRequestModel, TopAlbumsResponse>(localSource, networkSource)
