package com.tobiapplications.artista.utils.repository.base

import retrofit2.Response
import java.util.concurrent.ConcurrentHashMap

open class BaseRepository<I, T> constructor(private val localSource: LocalSourceDelegate<I, T>,
                                            private val networkSource: NetworkSourceDelegate<I, T>) : RepositoryDelegate<I, T> {

    private val REQUEST_CODE = "request_code"
    private val requestCache = ConcurrentHashMap<String, Response<T>>()

    override suspend fun getData(input: I): Response<T> {
        if (localSource.dataCached()) {
            return localSource.requestData(input).await()
        }

        if (requestCache[REQUEST_CODE] is Response<T>) {
            return requestCache[REQUEST_CODE]!!
        }

        val request = networkSource.requestData(input).await()
        localSource.setData(request)

        return request
    }

    override fun clear() {
        localSource.clear()
    }
}