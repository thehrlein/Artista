package com.tobiapplications.artista.di

import com.tobiapplications.artista.domain.*
import com.tobiapplications.artista.utils.general.SearchQueryStore
import com.tobiapplications.artista.utils.network.NetworkManagerDelegate
import com.tobiapplications.artista.utils.repository.topalbums.TopAlbumsRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.multiton

/**
 * Created by tobias.hehrlein on 15.08.2019.
 */

val useCaseModule = Kodein.Module("useCaseModule") {

    bind<GetAlbumTracksUseCase>() with multiton { networkManager: NetworkManagerDelegate -> GetAlbumTracksUseCase(networkManager) }
    bind<GetLastSearchQueryUseCase>() with multiton { searchQueryStore: SearchQueryStore -> GetLastSearchQueryUseCase(searchQueryStore) }
    bind<GetTopAlbumsUseCase>() with multiton { topAlbumsRepository: TopAlbumsRepository -> GetTopAlbumsUseCase(topAlbumsRepository) }
    bind<SearchArtistsUseCase>() with multiton { networkManager: NetworkManagerDelegate -> SearchArtistsUseCase(networkManager) }
    bind<StoreLastSearchQueryUseCase>() with multiton { searchQueryStore: SearchQueryStore -> StoreLastSearchQueryUseCase(searchQueryStore) }
}

