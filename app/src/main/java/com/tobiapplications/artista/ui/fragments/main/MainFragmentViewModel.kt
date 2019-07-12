package com.tobiapplications.artista.ui.fragments.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tobiapplications.artista.model.AlbumEntry
import com.tobiapplications.artista.utils.persistence.room.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(private val albumRepository: AlbumRepository) : ViewModel() {

    val favoriteAlbums : LiveData<List<AlbumEntry>> = albumRepository.favoriteAlbums

    fun insert(albumEntry: AlbumEntry) = viewModelScope.launch(Dispatchers.IO) {
        albumRepository.insert(albumEntry)
    }

    fun delete(albumEntry: AlbumEntry) = viewModelScope.launch(Dispatchers.IO) {
        albumRepository.delete(albumEntry)
    }
}
