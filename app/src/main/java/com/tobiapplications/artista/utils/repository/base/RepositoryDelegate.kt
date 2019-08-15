package com.tobiapplications.artista.utils.repository.base

import retrofit2.Response

interface RepositoryDelegate<I, T> {

    suspend fun getData(input: I) : Response<T>
    fun clear()
}