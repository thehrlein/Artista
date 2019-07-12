package com.tobiapplications.artista.utils.general

import com.tobiapplications.artista.utils.persistence.SharedPreferencesManager
import javax.inject.Inject
import javax.inject.Singleton

/**
 *  Created by tobiashehrlein on 2019-07-11
 */
@Singleton
class SearchQueryStore @Inject constructor(private val sharedPreferencesManager: SharedPreferencesManager) {


    fun setLastQuery(query: String) {
        sharedPreferencesManager.setPreference(ArtistaConstants.LAST_SEARCH_QUERY, query)
    }

    fun getLastQuery(): String {
        return sharedPreferencesManager.getPreference(ArtistaConstants.LAST_SEARCH_QUERY, ArtistaConstants.DEFAULT_STRING)
    }
}