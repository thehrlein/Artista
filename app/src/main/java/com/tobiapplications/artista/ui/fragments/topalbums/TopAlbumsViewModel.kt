package com.tobiapplications.artista.ui.fragments.topalbums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tobiapplications.artista.domain.GetTopAlbumsUseCase
import com.tobiapplications.artista.model.AlbumEntry
import com.tobiapplications.artista.model.topalbums.AlbumAttributes
import com.tobiapplications.artista.model.topalbums.TopAlbumRequestModel
import com.tobiapplications.artista.model.topalbums.TopAlbumsResponse
import com.tobiapplications.artista.utils.extension.map
import com.tobiapplications.artista.utils.mvvm.Result
import com.tobiapplications.artista.utils.persistence.room.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class TopAlbumsViewModel @Inject constructor(private val getTopAlbumsUseCase: GetTopAlbumsUseCase,
                                             private val albumRepository: AlbumRepository) : ViewModel() {


    val favoriteAlbums : LiveData<List<AlbumEntry>> = albumRepository.favoriteAlbums

    private var topAlbumsWrapper = MutableLiveData<List<AlbumEntry>>()
    val topAlbums : LiveData<List<AlbumEntry>>

    private var albumAttributesWrapper = MutableLiveData<AlbumAttributes>()
    var albumAttributes : LiveData<AlbumAttributes>

    init {
        topAlbums = topAlbumsWrapper
        albumAttributes = albumAttributesWrapper
    }

    fun getTopAlbums(artist: String, albumPage: Int, albumsPerRequest: Int) {
        viewModelScope.launch {
           when (val response = getTopAlbumsUseCase.getResponse(TopAlbumRequestModel(artist, albumPage, albumsPerRequest,true))) {
               is Result.Success -> {
                   val albumResponse = response.data
                   albumAttributesWrapper.postValue(albumResponse.topAlbums.albumAttributes)
                   topAlbumsWrapper.postValue(response.data.topAlbums.album.map { AlbumEntry(it.name, it.playCount, it.url, it.artist.artist, it.images.lastOrNull()?.url.orEmpty()) })
               }
           }
        }
    }

    fun insert(albumEntry: AlbumEntry) = viewModelScope.launch(Dispatchers.IO) {
        albumRepository.insert(albumEntry)
    }

    fun delete(albumEntry: AlbumEntry) = viewModelScope.launch(Dispatchers.IO) {
        albumRepository.delete(albumEntry)
    }
}