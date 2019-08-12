package com.tobiapplications.artista.ui.fragments.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tobiapplications.artista.domain.GetAlbumTracksUseCase
import com.tobiapplications.artista.model.AlbumEntry
import com.tobiapplications.artista.model.tracks.AlbumTracksRequestModel
import com.tobiapplications.artista.model.tracks.Track
import com.tobiapplications.artista.utils.extension.map
import com.tobiapplications.artista.utils.mvvm.BaseViewModel
import com.tobiapplications.artista.utils.persistence.room.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val getAlbumTracksUseCase: GetAlbumTracksUseCase,
                                          private val albumRepository: AlbumRepository) : BaseViewModel() {

    private val tracksResult = MutableLiveData<List<Track>>()
    var tracks : LiveData<List<Track>>

    init {
        tracks = tracksResult.map { it }
    }

    fun getAlbumTracks(artist: String, name: String) {
        launch {
            val data = getAlbumTracksUseCase.getData(AlbumTracksRequestModel(artist, name)).await()
            tracksResult.postValue(data.body()?.album?.tracks?.tracks)
        }
    }

    fun updateAlbumEntry(album: AlbumEntry) = viewModelScope.launch(Dispatchers.IO) {
        albumRepository.update(album)
    }

    fun insert(albumEntry: AlbumEntry) = viewModelScope.launch(Dispatchers.IO) {
        albumRepository.insert(albumEntry)
    }

    fun delete(albumEntry: AlbumEntry) = viewModelScope.launch(Dispatchers.IO) {
        albumRepository.delete(albumEntry)
    }
}