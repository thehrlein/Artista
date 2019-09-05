package com.tobiapplications.artista.ui.fragments.detail

import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.LayerDrawable
import android.net.Uri
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.MotionEvent
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.tobiapplications.artista.R
import com.tobiapplications.artista.model.AlbumEntry
import com.tobiapplications.artista.model.tracks.Track
import com.tobiapplications.artista.ui.base.BaseFragment
import com.tobiapplications.artista.ui.viewhandler.TracksAdapter
import com.tobiapplications.artista.utils.extension.*
import com.tobiapplications.artista.utils.general.ArtistaConstants
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment() {

    private val viewModel: DetailViewModel by viewModel()
    private lateinit var album: AlbumEntry

    companion object {
        fun newInstance(albumEntry: AlbumEntry) : DetailFragment {
            val bundle = Bundle().apply { putSerializable(ArtistaConstants.ALBUM, albumEntry) }
            return DetailFragment().apply { arguments = bundle }
        }
    }

    override fun init() {
        initViewModel()
        initViews()
    }

    private fun initViews() {
        album = arguments?.getSerializable(ArtistaConstants.ALBUM) as AlbumEntry

        album.apply {
            loadTracks(trackList, artist, name)
            setToolbarTitle(name)
            setAlbumCover(image)
            albumName.text = name
            artistName.text = artist
            albumUrl.onClick { openWeb(url) }
            playCountText.text = playCount.formatThousand()
            if (isFavorite) setGoldStar() else resetStar()
        }

        rating.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                consume { onRatingClicked() }
            } else {
                false
            }
        }

        albumUrl.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun loadTracks(trackList: List<Track>, artist: String, name: String) {
        if (trackList.isEmpty()) {
            viewModel.getAlbumTracks(artist, name)
        } else {
            setTracksAdapter(trackList)
            trackLoadingGroup.hide()
        }
    }

    private fun setAlbumCover(image: String) {
        if (image.isNotEmpty()) {
            Glide.with(requireContext()).load(image).into(albumCover)
            albumCover.show()
        } else {
            albumCover.setGone()
        }
    }

    private fun initViewModel() {
        viewModel.tracks.observe(this, Observer { setTracks(it) })
    }

    private fun setTracks(tracks: List<Track>) {
        trackLoadingGroup.hide()
        if (tracks.isNotEmpty()) {
            setTracksAdapter(tracks)
            album.trackList = tracks
            viewModel.updateAlbumEntry(album)
        } else {
            noTracksAvailable.show()
        }
    }

    private fun setTracksAdapter(tracks: List<Track>) {
        listView.adapter = TracksAdapter(requireContext(), tracks, openTrack = { openWeb(it) })
        trackGroup.show()
    }

    private fun openWeb(url: String) {
        val openUrl = Intent(Intent.ACTION_VIEW).apply { data = Uri.parse(url) }
        startActivity(openUrl)
    }

    private fun onRatingClicked() {
        if (rating.rating == 0f) {
            like()
        } else {
            dislike()
        }
    }

    private fun like() {
        setGoldStar()
       viewModel.insert(album)
        showSnackBar(getString(R.string.top_albums_marked_as_favorite, album.name), coordinatorLayout)
    }

    private fun setGoldStar() {
        rating.rating = 1f
        val layerDrawable = rating.progressDrawable as LayerDrawable
        layerDrawable.getDrawable(2).colorFilter =
            PorterDuffColorFilter(getColor(R.color.colorGold), PorterDuff.Mode.SRC_ATOP)
    }

    private fun dislike() {
        resetStar()
        viewModel.delete(album)
        showSnackBar(getString(R.string.top_albums_unmarked_as_favorite, album.name), coordinatorLayout)
    }

    private fun resetStar() {
        rating.rating = 0f
    }

    override fun getLayout(): Int {
        return R.layout.fragment_detail
    }

    override fun isToolbarBackButtonEnabled(): Boolean {
        return true
    }
}

