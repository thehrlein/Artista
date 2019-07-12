package com.tobiapplications.artista.ui.viewhandler.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.tobiapplications.artista.R
import com.tobiapplications.artista.model.AlbumEntry
import com.tobiapplications.artista.ui.viewholder.AlbumViewHolder
import com.tobiapplications.artista.utils.general.DisplayableItem

class AlbumDelegate(private val onAlbumLikeListener: (AlbumEntry, Boolean) -> Unit,
                    private val onAlbumClickListener: (AlbumEntry) -> Unit) : AdapterDelegate<List<DisplayableItem>>(){

    override fun isForViewType(items: List<DisplayableItem>, position: Int): Boolean {
        return items[position] is AlbumEntry
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return AlbumViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.viewholder_album, parent, false), onAlbumLikeListener, onAlbumClickListener)
    }

    override fun onBindViewHolder(items: List<DisplayableItem>, position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {
        val album = items[position] as? AlbumEntry ?: return
        (holder as? AlbumViewHolder)?.setAlbum(album)
    }
}

