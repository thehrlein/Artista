package com.tobiapplications.artista.utils.mvvm

import androidx.lifecycle.MediatorLiveData


/**
 * Executes business logic in its getResponse method and keep posting updates to the result as
 * [Result<R>].
 * Handling an exception (emit [Result.Error] to the result) is the subclasses's responsibility.
 */
abstract class MediatorUseCase<in I, O> {

        protected val result = MediatorLiveData<Result<O>>()

        // open so that mock instances can mock this method
        open fun observe(): MediatorLiveData<Result<O>> {
            return result
        }

        abstract fun execute(parameters: I)
    }