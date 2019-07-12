package com.tobiapplications.artista.di.module

import com.tobiapplications.artista.ui.activities.MainActivity
import com.tobiapplications.artista.ui.fragments.detail.DetailFragment
import com.tobiapplications.artista.ui.fragments.main.MainFragment
import com.tobiapplications.artista.ui.fragments.search.SearchFragment
import com.tobiapplications.artista.ui.fragments.topalbums.TopAlbumsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder  {

    @ContributesAndroidInjector
    abstract fun bindMainActivity() : MainActivity

    @ContributesAndroidInjector
    abstract fun bindMainFragment() : MainFragment

    @ContributesAndroidInjector
    abstract fun bindSearchFragment() : SearchFragment

    @ContributesAndroidInjector
    abstract fun bindDetailFragment() : DetailFragment

    @ContributesAndroidInjector
    abstract fun bindTopAlbumsFragment() : TopAlbumsFragment

}
