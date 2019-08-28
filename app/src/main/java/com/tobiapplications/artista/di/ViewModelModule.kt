package com.tobiapplications.artista.di

import com.tobiapplications.artista.ui.activities.MainActivityViewModel
import com.tobiapplications.artista.ui.fragments.detail.DetailViewModel
import com.tobiapplications.artista.ui.fragments.main.MainFragmentViewModel
import com.tobiapplications.artista.ui.fragments.search.SearchViewModel
import com.tobiapplications.artista.ui.fragments.topalbums.TopAlbumsViewModel
import com.tobiapplications.artista.utils.extension.bindViewModel
import com.tobiapplications.artista.utils.mvvm.ViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton


val viewModelModule = Kodein.Module("viewModelModule") {

    bind<ViewModelFactory>() with singleton { ViewModelFactory(this.kodein) }
    bindViewModel<MainActivityViewModel>() with singleton { MainActivityViewModel(instance()) }
    bindViewModel<MainFragmentViewModel>() with singleton { MainFragmentViewModel(instance()) }
    bindViewModel<SearchViewModel>() with singleton { SearchViewModel(instance(), instance(), instance()) }
    bindViewModel<TopAlbumsViewModel>() with singleton { TopAlbumsViewModel(instance(), instance()) }
    bindViewModel<DetailViewModel>() with singleton { DetailViewModel(instance(), instance()) }

}