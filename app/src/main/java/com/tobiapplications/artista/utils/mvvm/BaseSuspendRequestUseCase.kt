package com.tobiapplications.artista.utils.mvvm

import com.tobiapplications.artista.model.exception.RequestFailedException
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Deferred
import retrofit2.Response

/**
 * Created by tobias.hehrlein on 12.08.2019.
 */
abstract class BaseSuspendRequestUseCase<I, O> : SuspendMediatorUseCase<I, O>(){

    companion object {
        const val REQUEST_FAILED = 500
    }

    private val compositeDisposable = CompositeDisposable()

    protected abstract suspend fun getData(input: I): Deferred<Response<O>>

    override suspend fun execute(parameters: I) : Deferred<Response<O>> {
        val data = getData(parameters)
        return data

       // onSuccess(data)

//        compositeDisposable.add(getData(parameters)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({ response -> onSuccess(response) }, { throwable -> onError(throwable, REQUEST_FAILED) })
//        )
    }

    private fun onSuccess(response: Response<O>) {
        if (response.isSuccessful) {
            result.postValue(Result.Success(response.body()!!))
        } else {
            onError(RequestFailedException("${this.javaClass.simpleName} was not successful"), response.code())
        }
    }

    private fun onError(throwable: Throwable, code: Int) {
        result.postValue(Result.Error(RequestFailedException(throwable.message, code)))
    }
}