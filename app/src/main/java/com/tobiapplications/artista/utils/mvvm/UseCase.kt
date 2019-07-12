package com.tobiapplications.artista.utils.mvvm

import androidx.lifecycle.MutableLiveData
import timber.log.Timber

abstract class UseCase<in P, R> {

    private val taskScheduler = DefaultScheduler

    /** Executes the use case asynchronously and returns a [Result] in a new LiveData object.
     * @return an observable [MutableLiveData] with a [Result].
     * @param param the input parameters to run the use case with
     */
    operator fun invoke(param : P) : MutableLiveData<Result<R>> {
        val liveCallback = MutableLiveData<Result<R>>()
        this(param, liveCallback)
        return liveCallback
    }

    /** Executes the use case asynchronously and places the [Result] in a MutableLiveData
     * @param param the input parameters to run the use case with
     * @param liveData the MutableLiveData where the result is posted to
     */
    operator fun invoke(param : P, liveData: MutableLiveData<Result<R>>) {
        try {
            taskScheduler.execute {
               try {
                   execute(param).let {
                       liveData.postValue(Result.Success(it))
                   }
               } catch (e : Exception) {
                    Timber.d(e)
                    liveData.postValue(Result.Error(e))
               }
            }
        } catch (e : Exception) {
            Timber.d(e)
            liveData.postValue(Result.Error(e))
        }
    }

    /**
     * Override this to set the code to be executed.
     */
    protected abstract fun execute(param: P): R

}