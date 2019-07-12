package com.tobiapplications.artista.domain

import com.tobiapplications.artista.utils.general.SearchQueryStore
import com.tobiapplications.artista.utils.mvvm.UseCase
import javax.inject.Inject

class GetLastSearchQueryUseCase @Inject constructor(private val searchQueryStore: SearchQueryStore) : UseCase<Unit, String>() {

    override fun execute(param: Unit): String {
        return searchQueryStore.getLastQuery()
    }
}