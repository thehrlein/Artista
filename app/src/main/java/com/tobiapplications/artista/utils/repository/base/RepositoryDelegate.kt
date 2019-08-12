package com.tobiapplications.artista.utils.repository.base

import kotlinx.coroutines.Deferred
import retrofit2.Response

interface RepositoryDelegate<I, T> {

    suspend fun getData(input: I) : Deferred<Response<T>>
    fun clear()
}