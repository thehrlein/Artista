package com.tobiapplications.artista.di

import com.tobiapplications.artista.ui.activities.MainActivityViewModel
import com.tobiapplications.artista.ui.fragments.detail.DetailViewModel
import com.tobiapplications.artista.ui.fragments.main.MainFragmentViewModel
import com.tobiapplications.artista.ui.fragments.search.SearchViewModel
import com.tobiapplications.artista.ui.fragments.topalbums.TopAlbumsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {

    viewModel { MainActivityViewModel(get()) }
    viewModel { MainFragmentViewModel(get()) }
    viewModel { SearchViewModel(get(), get(), get()) }
    viewModel { TopAlbumsViewModel(get(), get()) }
    viewModel { DetailViewModel(get(), get()) }

}