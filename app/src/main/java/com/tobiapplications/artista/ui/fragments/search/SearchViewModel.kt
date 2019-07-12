package com.tobiapplications.artista.ui.fragments.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tobiapplications.artista.domain.GetLastSearchQueryUseCase
import com.tobiapplications.artista.domain.SearchArtistsUseCase
import com.tobiapplications.artista.domain.StoreLastSearchQueryUseCase
import com.tobiapplications.artista.model.searchartist.Artist
import com.tobiapplications.artista.model.searchartist.ArtistResponse
import com.tobiapplications.artista.utils.extension.map
import com.tobiapplications.artista.utils.mvvm.Result
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val searchArtistsUseCase: SearchArtistsUseCase,
                                          private val getLastSearchQueryUseCase: GetLastSearchQueryUseCase,
                                          private val storeLastSearchQueryUseCase: StoreLastSearchQueryUseCase) : ViewModel() {

    val artists : LiveData<List<Artist>>
    private val lastSearchQueryResult = MutableLiveData<Result<String>>()
    val lastSearchQuery : LiveData<String>

    init {
        artists = searchArtistsUseCase.observe().map {
            (it as? Result.Success<ArtistResponse>)?.data?.results?.artistMatches?.artists.orEmpty()
        }

        lastSearchQuery = lastSearchQueryResult.map {
            (it as? Result.Success<String>)?.data.orEmpty()
        }

        getLastSearchQueryUseCase(Unit, lastSearchQueryResult)
    }

    fun searchArtists(query: String) {
        searchArtistsUseCase.execute(query)
    }

    fun storeLastSearchQuery(query: String) {
        storeLastSearchQueryUseCase(query)
        getLastSearchQueryUseCase(Unit, lastSearchQueryResult)
    }
}