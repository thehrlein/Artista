package com.tobiapplications.artista.utils.mvvm

import com.tobiapplications.artista.model.exception.RequestFailedException
import com.tobiapplications.artista.utils.repository.base.BaseRepository
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Response

/**
 * I = Input of useCase
 * O = Output of useCase
 * R = Response of request
 */
abstract class BaseRepositoryRequestUseCase<I, O, R>(private val repo: BaseRepository<I, R>) : SuspendMediatorUseCase<I, O>(), BaseRepositoryRequestUseCaseDelegate<R> {

    private val compositeDisposable = CompositeDisposable()

    override suspend fun execute(parameters: I) {
        val data = repo.getData(parameters).await()
        onSuccess(data)
    }

    override fun onSuccess(response: Response<R>) {
        if (response.isSuccessful) {
            result.postValue(Result.Success(transformResponse(response.body())))
        } else {
            onFailure(RequestFailedException("${this.javaClass.simpleName} was not successful"))
        }
    }

    override fun onFailure(throwable: Throwable) {
        result.postValue(Result.Error(RequestFailedException(throwable.message)))
    }

    fun clear() {
        repo.clear()
    }

    /**
     * Method to transform response to whatever is needed
     */
    abstract fun transformResponse(input: R?) : O
}

