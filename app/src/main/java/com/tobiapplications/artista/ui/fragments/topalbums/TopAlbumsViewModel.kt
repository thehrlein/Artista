package com.tobiapplications.artista.ui.fragments.topalbums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tobiapplications.artista.domain.GetTopAlbumsUseCase
import com.tobiapplications.artista.model.AlbumEntry
import com.tobiapplications.artista.model.topalbums.AlbumAttributes
import com.tobiapplications.artista.model.topalbums.TopAlbumRequestModel
import com.tobiapplications.artista.utils.extension.map
import com.tobiapplications.artista.utils.mvvm.BaseViewModel
import com.tobiapplications.artista.utils.persistence.room.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class TopAlbumsViewModel @Inject constructor(private val getTopAlbumsUseCase: GetTopAlbumsUseCase,
                                             private val albumRepository: AlbumRepository) : BaseViewModel() {


    private val favoriteAlbumsResult = MutableLiveData<List<AlbumEntry>>()
    val favoriteAlbums : LiveData<List<AlbumEntry>> = albumRepository.favoriteAlbums
    val topAlbums : LiveData<List<AlbumEntry>>
    var albumAttributes = MutableLiveData<AlbumAttributes>()

    init {
        topAlbums = favoriteAlbumsResult.map { it }
    }

    fun getTopAlbums(artist: String, albumPage: Int, albumsPerRequest: Int) {
        launch {
            val data = getTopAlbumsUseCase.execute(TopAlbumRequestModel(artist, albumPage, albumsPerRequest,true)).await().body()
            albumAttributes.value = data?.topAlbums?.albumAttributes
            data?.topAlbums?.album?.map { AlbumEntry(it.name, it.playCount, it.url, it.artist.artist, it.images.lastOrNull()?.url.orEmpty()) }.orEmpty()
            favoriteAlbumsResult.postValue(data?.topAlbums?.album?.map { AlbumEntry(it.name, it.playCount, it.url, it.artist.artist, it.images.lastOrNull()?.url.orEmpty()) }.orEmpty())
        }
    }

    fun insert(albumEntry: AlbumEntry) = viewModelScope.launch(Dispatchers.IO) {
        albumRepository.insert(albumEntry)
    }

    fun delete(albumEntry: AlbumEntry) = viewModelScope.launch(Dispatchers.IO) {
        albumRepository.delete(albumEntry)
    }
}