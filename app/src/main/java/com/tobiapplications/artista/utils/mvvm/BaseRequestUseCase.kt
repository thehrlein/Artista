package com.tobiapplications.artista.utils.mvvm

import com.tobiapplications.artista.model.exception.RequestFailedException
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

abstract class BaseRequestUseCase<I, O> : MediatorUseCase<I, O?>() {

    companion object {
        const val REQUEST_FAILED = 500
    }

    private val compositeDisposable = CompositeDisposable()

    protected abstract fun getData(input: I) : Single<Response<O>>

    override fun execute(parameters: I) {
        compositeDisposable.add(getData(parameters)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({ response -> onSuccess(response)}, { throwable -> onError(throwable, REQUEST_FAILED) }))
    }

    private fun onSuccess(response: Response<O>) {
        if (response.isSuccessful) {
            result.postValue(Result.Success(response.body()))
        } else {
            onError(RequestFailedException("${this.javaClass.simpleName} was not successful"), response.code())
        }
    }

    private fun onError(throwable: Throwable, code: Int) {
        result.postValue(Result.Error(RequestFailedException(throwable.message, code)))
    }
}