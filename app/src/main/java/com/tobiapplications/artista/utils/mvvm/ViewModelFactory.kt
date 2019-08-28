package com.tobiapplications.artista.utils.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.DKodein
import org.kodein.di.generic.instanceOrNull

class ViewModelFactory constructor(private val kodein: DKodein): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
         kodein.instanceOrNull<ViewModel>(tag = modelClass.simpleName) as T? ?: modelClass.newInstance()

}