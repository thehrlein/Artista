package com.tobiapplications.artista.di

import com.tobiapplications.artista.domain.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

/**
 * Created by tobias.hehrlein on 15.08.2019.
 */

val useCaseModule = Kodein.Module("useCaseModule") {

    bind() from singleton { GetAlbumTracksUseCase(instance()) }
    bind() from singleton { GetLastSearchQueryUseCase(instance()) }
    bind() from singleton { GetTopAlbumsUseCase(instance()) }
    bind() from singleton { SearchArtistsUseCase(instance()) }
    bind() from singleton { StoreLastSearchQueryUseCase(instance()) }
}

