package com.tobiapplications.artista.di

import com.tobiapplications.artista.utils.general.CoreService
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

/**
 * Created by tobias.hehrlein on 15.08.2019.
 */
val appModule = Kodein.Module("appModule") {

    bind() from singleton { CoreService() }
}
