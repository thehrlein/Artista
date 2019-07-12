package com.tobiapplications.artista.ui.viewholder

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.LayerDrawable
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tobiapplications.artista.R
import com.tobiapplications.artista.model.AlbumEntry
import com.tobiapplications.artista.utils.extension.*
import kotlinx.android.synthetic.main.viewholder_album.view.*

class AlbumViewHolder(private val view: View,
                      private val albumLikeListener: (AlbumEntry, Boolean) -> Unit,
                      private val onAlbumClickListener: (AlbumEntry) -> Unit) : RecyclerView.ViewHolder(view) {

    private lateinit var albumEntry : AlbumEntry

    init {
        view.rating.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                consume { onRatingClicked() }
            } else {
                false
            }
        }

        view.clickTile.onClick { onAlbumClickListener(albumEntry) }
    }

    private fun onRatingClicked() {
        if (view.rating.rating == 0f) {
            like()
        } else {
            dislike()
        }
    }

    private fun like() {
        setGoldStar()

        albumLikeListener(albumEntry, true)
    }

    private fun setGoldStar() {
        view.rating.rating = 1f
        val layerDrawable = view.rating.progressDrawable as LayerDrawable
        layerDrawable.getDrawable(2).colorFilter =
            PorterDuffColorFilter(getColor(R.color.colorGold), PorterDuff.Mode.SRC_ATOP)
    }

    private fun dislike() {
        resetStar()

        albumLikeListener(albumEntry, false)
    }

    private fun resetStar() {
        view.rating.rating = 0f
    }

    fun setAlbum(albumEntry: AlbumEntry) {
        this.albumEntry = albumEntry
        view.albumTitle.text = albumEntry.name
        if (albumEntry.image.isEmpty()) {
            view.noCover.show()
            view.albumCover.hide()
        } else {
            view.noCover.hide()
            view.albumCover.show()
            Glide.with(view).load(albumEntry.image).placeholder(R.drawable.ic_album_placeholder).into(view.albumCover)
        }

        if (albumEntry.isFavorite) setGoldStar() else resetStar()

        view.progressBar.hide()
    }
}

