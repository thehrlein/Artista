package com.tobiapplications.artista.ui.fragments.search

import androidx.lifecycle.Observer
import com.tobiapplications.artista.R
import com.tobiapplications.artista.model.searchartist.Artist
import com.tobiapplications.artista.ui.base.BaseFragment
import com.tobiapplications.artista.ui.fragments.topalbums.TopAlbumsFragment
import com.tobiapplications.artista.ui.viewhandler.SearchQueryAdapter
import com.tobiapplications.artista.utils.extension.*
import com.tobiapplications.artista.utils.network.NetworkUtils
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.view_search_query_single_item_view.view.*

class SearchFragment : BaseFragment() {

    private lateinit var viewModel: SearchViewModel
    private var searchAdapter: SearchQueryAdapter? = null

    companion object {
        fun newInstance() : SearchFragment {
            return SearchFragment()
        }
    }

    override fun init() {
        initViewModel()
        initViews()
    }

    private fun initViews() {
        searchAdapter = SearchQueryAdapter(requireContext(), objects = emptyList())
        searchAutoCompleteText.setAdapter(searchAdapter)
        searchAutoCompleteText.setOnItemClickListener { _, view, _, _ ->
            val query = view.text.text.toString()
            viewModel.storeLastSearchQuery(query)
            replaceFragment(TopAlbumsFragment.newInstance(query))
        }
        searchButton.onClick {
            searchAutoCompleteText.closeKeyboard()
            if (NetworkUtils.isConnectedToInternet(requireContext())) {
                progressBar.show()
                searchAdapter?.clearCurrentArtists()
                viewModel.searchArtists(searchAutoCompleteText.text.toString())
            } else {
                toast(R.string.general_no_internet_connection)
            }
        }

        searchAutoCompleteText.requestFocus()
    }

    private fun initViewModel() {
        viewModel = obtainViewModel()
        viewModel.artists.observe(viewLifecycleOwner, Observer { setArtists(it) })
        viewModel.lastSearchQuery.observe(viewLifecycleOwner, Observer {
            searchAutoCompleteText.setText(it)
            searchAutoCompleteText.setSelection(it.length)
        })
    }

    private fun setArtists(artists: List<Artist>) {
        searchAdapter?.setArtists(artists)
        progressBar.setGone()
        searchAutoCompleteText.showDropDown()
    }

    override fun isToolbarBackButtonEnabled(): Boolean {
        return true
    }

    override fun getToolbarTitle(): String {
        return getString(R.string.search_toolbar_title)
    }

    override fun getLayout(): Int {
        return R.layout.fragment_search
    }
}

