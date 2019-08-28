package com.tobiapplications.artista.domain

import com.tobiapplications.artista.utils.general.SearchQueryStore
import com.tobiapplications.artista.utils.mvvm.UseCase

class StoreLastSearchQueryUseCase constructor(private val searchQueryStore: SearchQueryStore) : UseCase<String, Unit>() {

    override fun execute(param: String) {
        searchQueryStore.setLastQuery(param)
    }
}