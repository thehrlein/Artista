package com.tobiapplications.artista.ui.fragments.topalbums

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tobiapplications.artista.R
import com.tobiapplications.artista.model.AlbumEntry
import com.tobiapplications.artista.model.topalbums.AlbumAttributes
import com.tobiapplications.artista.ui.base.BaseFragment
import com.tobiapplications.artista.ui.fragments.detail.DetailFragment
import com.tobiapplications.artista.ui.viewhandler.AlbumAdapter
import com.tobiapplications.artista.utils.extension.*
import com.tobiapplications.artista.utils.general.ArtistaConstants
import kotlinx.android.synthetic.main.fragment_top_albums.*

class TopAlbumsFragment : BaseFragment() {

    private lateinit var viewModel: TopAlbumsViewModel
    private var albumAdapter : AlbumAdapter? = null
    private var albumAttributes: AlbumAttributes = AlbumAttributes(0, 1)
    private var loadResult = true
    private var favorites = emptyList<AlbumEntry>()

    companion object {
        fun newInstance(artist: String) : TopAlbumsFragment {
            val bundle = Bundle().apply { putString(ArtistaConstants.ARTIST, artist) }
            return TopAlbumsFragment().apply { arguments = bundle }
        }

        const val ALBUMS_PER_REQUEST = 50
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        albumAdapter = AlbumAdapter( { album, like -> onAlbumLikeChanged(album, like) }, { onAlbumClicked(it) } )
    }

    override fun init() {
        initRecyclerView()
        initViewModel()
        initViews()
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = albumAdapter
        }
    }

    private fun nextPageAvailable(): Boolean {
        return albumAttributes.page < albumAttributes.totalPages
    }

    private fun loadAlbums() {
        val artist = arguments?.getString(ArtistaConstants.ARTIST)

        when {
            !loadResult -> return
            artist == null -> toast(R.string.general_error_occured)
            else -> viewModel.getTopAlbums(artist, albumAttributes.page + 1, ALBUMS_PER_REQUEST)
        }
    }

    private fun initViewModel() {
        viewModel = obtainViewModel()
        viewModel.topAlbums.observe(viewLifecycleOwner, Observer { setAlbums(it) })
        viewModel.albumAttributes.observe(viewLifecycleOwner, Observer { this.albumAttributes = it })
        viewModel.favoriteAlbums.observe(viewLifecycleOwner, Observer { setFavorites(it) })
    }

    private fun setFavorites(it: List<AlbumEntry>) {
        this.favorites = it
        albumAdapter?.refreshFavorites(it)
    }

    private fun setAlbums(it: List<AlbumEntry>?) {
        if (it == null) {
            return
        }
        progressBar.hide()

        if (it.isEmpty()) {
            recyclerView.hide()
            noAlbums.show()
        } else {
            loadResult = true
            albumAdapter?.addItems(it, favorites)
            recyclerView.show()
            recyclerView.addOnScrollListener(scrollListener)
            noAlbums.hide()
        }
    }

    private fun initViews() {
        loadAlbums()
        scrollUpButton.onClick { recyclerView.smoothScrollToPosition(0) }
    }

    private fun onAlbumLikeChanged(albumEntry: AlbumEntry, liked: Boolean) {
        showSnackBar(if (liked) getString(R.string.top_albums_marked_as_favorite, albumEntry.name) else getString(R.string.top_albums_unmarked_as_favorite, albumEntry.name), coordinatorLayout)
        if (liked) viewModel.insert(albumEntry) else viewModel.delete(albumEntry)
    }

    private fun onAlbumClicked(albumEntry: AlbumEntry) {
        replaceFragment(DetailFragment.newInstance(albumEntry))
    }

    override fun isToolbarBackButtonEnabled(): Boolean {
        return true
    }

    override fun getToolbarTitle(): String {
        return arguments?.getString(ArtistaConstants.ARTIST) ?: getString(R.string.top_albums_toolbar_title)
    }

    override fun getLayout(): Int {
        return R.layout.fragment_top_albums
    }

    private val scrollListener =
        object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = (recyclerView.layoutManager as? GridLayoutManager) ?: return
                val lastVisiblePos = layoutManager.findLastVisibleItemPosition()
                if (lastVisiblePos >= ((albumAttributes.page)  * ALBUMS_PER_REQUEST) - (ALBUMS_PER_REQUEST / 2) && nextPageAvailable()) {
                    recyclerView.removeOnScrollListener(this)
                    loadAlbums()
                }

                // hide button if scroll down or already near top
                scrollUpButton.apply { if (dy >= 0 || lastVisiblePos < 10) hide() else show() }
            }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        loadResult = false
    }
}