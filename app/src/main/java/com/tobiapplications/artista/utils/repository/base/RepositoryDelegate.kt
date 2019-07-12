package com.tobiapplications.artista.utils.repository.base

import io.reactivex.Observable
import retrofit2.Response

interface RepositoryDelegate<I, T> {

    fun getData(input: I) : Observable<Response<T>>
    fun clear()
}