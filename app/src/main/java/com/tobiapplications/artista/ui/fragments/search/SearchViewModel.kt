package com.tobiapplications.artista.ui.fragments.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tobiapplications.artista.domain.GetLastSearchQueryUseCase
import com.tobiapplications.artista.domain.SearchArtistsUseCase
import com.tobiapplications.artista.domain.StoreLastSearchQueryUseCase
import com.tobiapplications.artista.model.searchartist.Artist
import com.tobiapplications.artista.utils.extension.map
import com.tobiapplications.artista.utils.mvvm.Result
import kotlinx.coroutines.launch

class SearchViewModel constructor(private val searchArtistsUseCase: SearchArtistsUseCase,
                                          private val getLastSearchQueryUseCase: GetLastSearchQueryUseCase,
                                          private val storeLastSearchQueryUseCase: StoreLastSearchQueryUseCase) : ViewModel() {

    private val artistWrapper = MutableLiveData<List<Artist>>()
    val artists : LiveData<List<Artist>>
    private val lastSearchQueryResult = MutableLiveData<Result<String>>()
    val lastSearchQuery : LiveData<String>

    init {
        artists = artistWrapper

        lastSearchQuery = lastSearchQueryResult.map {
            (it as? Result.Success<String>)?.data.orEmpty()
        }

        getLastSearchQueryUseCase(Unit, lastSearchQueryResult)
    }

    fun searchArtists(query: String) {
        viewModelScope.launch {
            when (val response = searchArtistsUseCase.getResponse(query)) {
                is Result.Success -> artistWrapper.postValue(response.data.results.artistMatches.artists)
            }
        }
    }

    fun storeLastSearchQuery(query: String) {
        storeLastSearchQueryUseCase(query)
        getLastSearchQueryUseCase(Unit, lastSearchQueryResult)
    }
}