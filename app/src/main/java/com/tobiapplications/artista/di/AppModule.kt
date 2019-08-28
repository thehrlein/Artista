package com.tobiapplications.artista.di

import com.tobiapplications.artista.utils.general.CoreService
import com.tobiapplications.artista.utils.network.NetworkManagerDelegate
import com.tobiapplications.artista.utils.repository.topalbums.TopAlbumsLocalSource
import com.tobiapplications.artista.utils.repository.topalbums.TopAlbumsNetworkSource
import com.tobiapplications.artista.utils.repository.topalbums.TopAlbumsRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.multiton
import org.kodein.di.generic.singleton

/**
 * Created by tobias.hehrlein on 15.08.2019.
 */
val appModule = Kodein.Module("appModule") {

    bind<CoreService>() with singleton { CoreService() }

    bind<TopAlbumsLocalSource>() with singleton { TopAlbumsLocalSource() }
    bind<TopAlbumsNetworkSource>() with multiton { networkManager: NetworkManagerDelegate -> TopAlbumsNetworkSource(networkManager) }
    bind<TopAlbumsRepository>() with multiton { localSource: TopAlbumsLocalSource, networkSource: TopAlbumsNetworkSource -> TopAlbumsRepository(localSource, networkSource) }

}
