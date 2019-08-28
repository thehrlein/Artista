package com.tobiapplications.artista.utils.mvvm

import com.tobiapplications.artista.model.exception.RequestFailedException
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Response

/**
 * Created by tobias.hehrlein on 12.08.2019.
 */
abstract class BaseRequestUseCase<I, O>{

    companion object {
        const val REQUEST_FAILED = 500
    }

    private val compositeDisposable = CompositeDisposable()

    protected abstract suspend fun getData(input: I): Response<O>

    suspend fun getResponse(parameters: I) : Result<O> {
        return try {
            val data = getData(parameters)

            if (data.isSuccessful) {
                Result.Success(data.body()!!)
            } else {
                Result.Error(RequestFailedException("${javaClass.simpleName} was not successful", data.code()))
            }
        } catch (e : Exception) {
            Result.Error(RequestFailedException(code = REQUEST_FAILED))
        }
    }
}