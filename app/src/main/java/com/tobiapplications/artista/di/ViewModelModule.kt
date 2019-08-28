package com.tobiapplications.artista.di

import androidx.lifecycle.ViewModelProvider
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
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton


val viewModelModule = Kodein.Module("viewModelModule") {

    bind() from singleton { ViewModelFactory(instance()) }
    bindViewModel<MainActivityViewModel>() with provider { MainActivityViewModel(instance()) }
    bindViewModel<MainFragmentViewModel>() with provider { MainFragmentViewModel(instance()) }
    bindViewModel<SearchViewModel>() with provider { SearchViewModel(instance(), instance(), instance()) }
    bindViewModel<TopAlbumsViewModel>() with provider { TopAlbumsViewModel(instance(), instance()) }
    bindViewModel<DetailViewModel>() with provider { DetailViewModel(instance(), instance()) }

}