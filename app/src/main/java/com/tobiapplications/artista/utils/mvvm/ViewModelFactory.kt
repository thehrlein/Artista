package com.tobiapplications.artista.utils.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory constructor(private val creators: @JvmSuppressWildcards Map<Class<out ViewModel>, ViewModel>): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val found = creators.entries.find { modelClass.isAssignableFrom(it.key) }
        val creator = found?.value
            ?: throw IllegalArgumentException("unknown model class $modelClass")
        try {
            @Suppress("UNCHECKED_CAST")
            return creator as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}