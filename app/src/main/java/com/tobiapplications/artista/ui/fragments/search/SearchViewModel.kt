package com.tobiapplications.artista.ui.fragments.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tobiapplications.artista.domain.GetLastSearchQueryUseCase
import com.tobiapplications.artista.domain.SearchArtistsUseCase
import com.tobiapplications.artista.domain.StoreLastSearchQueryUseCase
import com.tobiapplications.artista.model.searchartist.Artist
import com.tobiapplications.artista.utils.extension.map
import com.tobiapplications.artista.utils.mvvm.BaseViewModel
import com.tobiapplications.artista.utils.mvvm.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val searchArtistsUseCase: SearchArtistsUseCase,
                                          private val getLastSearchQueryUseCase: GetLastSearchQueryUseCase,
                                          private val storeLastSearchQueryUseCase: StoreLastSearchQueryUseCase) : BaseViewModel() {

    private val artistsResult = MutableLiveData<List<Artist>>()
    val artists : LiveData<List<Artist>>
    private val lastSearchQueryResult = MutableLiveData<Result<String>>()
    val lastSearchQuery : LiveData<String>

    init {
        artists = artistsResult.map {
            it.orEmpty()
        }

        lastSearchQuery = lastSearchQueryResult.map {
            (it as? Result.Success<String>)?.data.orEmpty()
        }

        getLastSearchQueryUseCase(Unit, lastSearchQueryResult)
    }

    fun searchArtists(query: String) {
        launch {
            val data = searchArtistsUseCase.execute(query)
            artistsResult.postValue(data.await().body()?.results?.artistMatches?.artists)
        }
    }

    fun storeLastSearchQuery(query: String) {
        storeLastSearchQueryUseCase(query)
        getLastSearchQueryUseCase(Unit, lastSearchQueryResult)
    }
}