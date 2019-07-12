package com.tobiapplications.artista.ui.viewhandler

import com.tobiapplications.artista.model.AlbumEntry
import com.tobiapplications.artista.ui.viewhandler.delegates.AlbumDelegate

class AlbumAdapter(onAlbumLikeListener: (AlbumEntry, Boolean) -> Unit, onAlbumClickListener: (AlbumEntry) -> Unit) : BaseRecyclerViewAdapter() {

    init {
        delegatesManager.addDelegate(AlbumDelegate(onAlbumLikeListener, onAlbumClickListener))
    }

    fun addItems(items: List<AlbumEntry>, favorites: List<AlbumEntry>?) {
        if (favorites == null) {
            super.addItems(items)
            return
        }
        items.filter { favorites.contains(it) }.forEach { it.isFavorite = true }
        super.addItems(items)
    }

    fun refreshFavorites(favorites: List<AlbumEntry>) {
        itemList
            .filterIsInstance(AlbumEntry::class.java)
            .forEachIndexed { index, album ->
                favorize(album, index, favorites.contains(album))
            }
    }

    private fun favorize(album: AlbumEntry, index: Int, favorize: Boolean) {
        if (album.isFavorite != favorize) {
            album.isFavorite = favorize
            notifyItemChanged(index)
        }
    }
}

