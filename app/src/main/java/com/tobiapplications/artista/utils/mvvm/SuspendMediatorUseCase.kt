package com.tobiapplications.artista.utils.mvvm

import androidx.lifecycle.MediatorLiveData


/**
 * Executes business logic in its execute method and keep posting updates to the result as
 * [Result<R>].
 * Handling an exception (emit [Result.Error] to the result) is the subclasses's responsibility.
 */
abstract class SuspendMediatorUseCase<in I, O> {

        protected val result = MediatorLiveData<Result<O>>()

        // open so that mock instances can mock this method
        open fun observe(): MediatorLiveData<Result<O>> {
            return result
        }

        abstract suspend fun execute(parameters: I)
    }