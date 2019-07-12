package com.tobiapplications.artista.ui.fragments.topalbums

import androidx.lifecycle.*
import com.tobiapplications.artista.domain.GetTopAlbumsUseCase
import com.tobiapplications.artista.model.AlbumEntry
import com.tobiapplications.artista.model.topalbums.*
import com.tobiapplications.artista.utils.extension.map
import com.tobiapplications.artista.utils.mvvm.Result
import com.tobiapplications.artista.utils.persistence.room.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class TopAlbumsViewModel @Inject constructor(private val getTopAlbumsUseCase: GetTopAlbumsUseCase,
                                             private val albumRepository: AlbumRepository) : ViewModel() {

    val favoriteAlbums : LiveData<List<AlbumEntry>> = albumRepository.favoriteAlbums
    val topAlbums : LiveData<List<AlbumEntry>>
    var albumAttributes = MutableLiveData<AlbumAttributes>()

    init {
        topAlbums = getTopAlbumsUseCase.observe().map { result ->
            val albumResponse = (result as? Result.Success<TopAlbumsResponse>)?.data
            albumAttributes.value = albumResponse?.topAlbums?.albumAttributes
            albumResponse?.topAlbums?.album?.map { AlbumEntry(it.name, it.playCount, it.url, it.artist.artist, it.images.lastOrNull()?.url.orEmpty()) }.orEmpty()
        }
    }

    fun getTopAlbums(artist: String, albumPage: Int, albumsPerRequest: Int) {
        getTopAlbumsUseCase.execute(TopAlbumRequestModel(artist, albumPage, albumsPerRequest,true))
    }

    fun insert(albumEntry: AlbumEntry) = viewModelScope.launch(Dispatchers.IO) {
        albumRepository.insert(albumEntry)
    }

    fun delete(albumEntry: AlbumEntry) = viewModelScope.launch(Dispatchers.IO) {
        albumRepository.delete(albumEntry)
    }
}