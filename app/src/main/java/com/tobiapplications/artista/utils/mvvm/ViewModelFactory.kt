package com.tobiapplications.artista.utils.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.Kodein
import org.kodein.di.TT
import org.kodein.di.direct

class ViewModelFactory constructor(private val injector: Kodein):
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
         injector.direct.Instance(TT(modelClass)) //<ViewModel>(tag = modelClass.simpleName) as T ?: modelClass.newInstance()

}