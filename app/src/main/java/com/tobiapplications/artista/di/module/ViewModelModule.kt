package com.tobiapplications.artista.di.module

import androidx.lifecycle.ViewModel
import com.tobiapplications.artista.di.scope.ViewModelKey
import com.tobiapplications.artista.ui.activities.MainActivityViewModel
import com.tobiapplications.artista.ui.fragments.detail.DetailViewModel
import com.tobiapplications.artista.ui.fragments.main.MainFragmentViewModel
import com.tobiapplications.artista.ui.fragments.search.SearchViewModel
import com.tobiapplications.artista.ui.fragments.topalbums.TopAlbumsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(viewModel: MainActivityViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainFragmentViewModel::class)
    abstract fun bindMainFragmentViewModel(viewModel: MainFragmentViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(viewModel: SearchViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TopAlbumsViewModel::class)
    abstract fun bindTopAlbumsViewModel(viewModel: TopAlbumsViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(viewModel: DetailViewModel) : ViewModel
}