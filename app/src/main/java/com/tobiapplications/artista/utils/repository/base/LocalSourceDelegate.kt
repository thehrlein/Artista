package com.tobiapplications.artista.utils.repository.base

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import retrofit2.Response

interface LocalSourceDelegate<I, T> : NetworkSourceDelegate<I, T> {

    fun get() : T?
    fun set(response: T?)
    fun dataCached() : Boolean {
        return get() != null
    }
    fun setData(response: Response<T>) {
        if (response.isSuccessful) {
            set(response.body())
        }
    }
    fun clear() {
        set(null)
    }

    override suspend fun requestData(input: I): Deferred<Response<T>> {
        return CompletableDeferred(Response.success(get()))
    }
}