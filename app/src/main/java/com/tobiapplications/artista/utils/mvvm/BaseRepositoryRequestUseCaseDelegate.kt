package com.tobiapplications.artista.utils.mvvm

import retrofit2.Response

interface BaseRepositoryRequestUseCaseDelegate<T> {

    fun onSuccess(response: Response<T>)
    fun onFailure(throwable: Throwable)
}