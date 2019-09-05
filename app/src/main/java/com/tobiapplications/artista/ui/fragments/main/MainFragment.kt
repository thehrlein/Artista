package com.tobiapplications.artista.ui.fragments.main

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.tobiapplications.artista.R
import com.tobiapplications.artista.model.AlbumEntry
import com.tobiapplications.artista.ui.base.BaseFragment
import com.tobiapplications.artista.ui.fragments.detail.DetailFragment
import com.tobiapplications.artista.ui.fragments.search.SearchFragment
import com.tobiapplications.artista.ui.viewhandler.AlbumAdapter
import com.tobiapplications.artista.utils.extension.*
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment() {

    private val viewModel: MainFragmentViewModel by viewModel()
    private var albumAdapter : AlbumAdapter? = null

    companion object {
        fun newInstance() : MainFragment {
            return MainFragment()
        }
    }

    override fun init() {
        initRecyclerView()
        initViewModel()
        initViews()
    }

    private fun initRecyclerView() {
        albumAdapter = AlbumAdapter({ album, like -> onAlbumLikeChanged(album, like) }, { onAlbumClicked(it) })
        recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = albumAdapter
        }
    }

    private fun onAlbumClicked(albumEntry: AlbumEntry) {
        replaceFragment(DetailFragment.newInstance(albumEntry))
    }

    private fun initViewModel() {
        viewModel.favoriteAlbums.observe(viewLifecycleOwner, Observer { setFavoriteAlbums(it) })
    }

    private fun initViews() {
        fabSearch.onClick { replaceFragment(SearchFragment.newInstance()) }
    }

    private fun setFavoriteAlbums(it: List<AlbumEntry>) {
        progressBar.hide()
        fabSearch.show()

        if (it.isEmpty()) {
            recyclerView.hide()
            noFavoriteAlbums.show()
        } else {
            it.forEach { it.isFavorite = true }
            albumAdapter?.clear()
            albumAdapter?.setItems(it)
            recyclerView.show()
            noFavoriteAlbums.hide()
        }
    }

    private fun onAlbumLikeChanged(albumEntry: AlbumEntry, liked: Boolean) {
        showSnackBar(if (liked) getString(R.string.top_albums_marked_as_favorite, albumEntry.name) else getString(R.string.top_albums_unmarked_as_favorite, albumEntry.name), coordinatorLayout)
        if (liked) viewModel.insert(albumEntry) else viewModel.delete(albumEntry)
    }

    override fun getToolbarTitle(): String {
        return getString(R.string.main_toolbar_title)
    }

    override fun getLayout(): Int {
        return R.layout.fragment_main
    }
}