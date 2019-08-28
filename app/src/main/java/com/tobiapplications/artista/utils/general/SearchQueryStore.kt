package com.tobiapplications.artista.utils.general

import com.tobiapplications.artista.utils.persistence.SharedPreferencesManager

/**
 *  Created by tobiashehrlein on 2019-07-11
 */
class SearchQueryStore constructor(private val sharedPreferencesManager: SharedPreferencesManager) {


    fun setLastQuery(query: String) {
        sharedPreferencesManager.setPreference(ArtistaConstants.LAST_SEARCH_QUERY, query)
    }

    fun getLastQuery(): String {
        return sharedPreferencesManager.getPreference(ArtistaConstants.LAST_SEARCH_QUERY, ArtistaConstants.DEFAULT_STRING)
    }
}