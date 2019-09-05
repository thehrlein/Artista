package com.tobiapplications.artista.di

import com.tobiapplications.artista.domain.*
import org.koin.dsl.module

/**
 * Created by tobias.hehrlein on 15.08.2019.
 */

val useCaseModule = module {

    single { GetAlbumTracksUseCase(get()) }
    single { GetLastSearchQueryUseCase(get()) }
    single { GetTopAlbumsUseCase(get()) }
    single { SearchArtistsUseCase(get()) }
    single { StoreLastSearchQueryUseCase(get()) }
}

