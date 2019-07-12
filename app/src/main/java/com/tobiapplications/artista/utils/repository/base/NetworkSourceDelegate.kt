package com.tobiapplications.artista.utils.repository.base

import io.reactivex.Single
import retrofit2.Response

interface NetworkSourceDelegate<I, T> {

    fun requestData(input: I): Single<Response<T>>
}