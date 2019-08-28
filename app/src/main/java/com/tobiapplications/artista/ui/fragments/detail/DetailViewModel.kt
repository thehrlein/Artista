package com.tobiapplications.artista.ui.fragments.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tobiapplications.artista.domain.GetAlbumTracksUseCase
import com.tobiapplications.artista.model.AlbumEntry
import com.tobiapplications.artista.model.tracks.AlbumTracksRequestModel
import com.tobiapplications.artista.model.tracks.Track
import com.tobiapplications.artista.utils.mvvm.Result
import com.tobiapplications.artista.utils.persistence.room.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel constructor(private val getAlbumTracksUseCase: GetAlbumTracksUseCase,
                                  private val albumRepository: AlbumRepository) : ViewModel() {

    private var tracksWrapper = MutableLiveData<List<Track>>()
    var tracks : LiveData<List<Track>>

    init {
        tracks = tracksWrapper
    }

    fun getAlbumTracks(artist: String, name: String) {
        viewModelScope.launch {
            when (val response = getAlbumTracksUseCase.getResponse(AlbumTracksRequestModel(artist, name))) {
                is Result.Success -> tracksWrapper.postValue(response.data.album?.tracks?.tracks ?: emptyList())
            }
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