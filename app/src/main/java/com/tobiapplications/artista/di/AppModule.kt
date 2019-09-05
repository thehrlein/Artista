package com.tobiapplications.artista.di

import com.tobiapplications.artista.utils.general.ArtistaConstants
import com.tobiapplications.artista.utils.general.CoreService
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Created by tobias.hehrlein on 15.08.2019.
 */
val appModule = module {
   single { CoreService() }
}


