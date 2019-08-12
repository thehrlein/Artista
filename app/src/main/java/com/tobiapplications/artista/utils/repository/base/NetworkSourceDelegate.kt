package com.tobiapplications.artista.utils.repository.base

import kotlinx.coroutines.Deferred
import retrofit2.Response

interface NetworkSourceDelegate<I, T> {

    suspend fun requestData(input: I): Deferred<Response<T>>
}