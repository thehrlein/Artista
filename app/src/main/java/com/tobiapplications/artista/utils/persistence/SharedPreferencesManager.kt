package com.tobiapplications.artista.utils.persistence

import android.content.SharedPreferences

class SharedPreferencesManager(private val sharedPreferences: SharedPreferences) {

    /**
     * Store value with given key in SharedPreferencesManager. Supports Integer, Long, Float, Boolean and String values.
     *
     * @param key
     * key name
     * @param preferenceValue
     * Preference value to set
     */
    fun setPreference(key: String, preferenceValue: Any) {
        val editor = sharedPreferences.edit()

        when (preferenceValue) {
            is Int -> editor.putInt(key, preferenceValue)
            is Long -> editor.putLong(key, preferenceValue)
            is Float-> editor.putFloat(key, preferenceValue)
            is Boolean -> editor.putBoolean(key, preferenceValue )
            is String -> editor.putString(key, preferenceValue)
            else -> return
        }

        editor.apply()
    }

    /**
     * Get value with given key of SharedPreferencesManager if exist. If not, default value stored with this key and will be returned
     *
     * @param key
     * Key to get respective value of SharedPreferencesManager
     * @param defaultValue
     * Default value which is stored in SharedPreferencesManager if no value for the given key is set yet.
     * @return
     * value which is stored in SharedPreferencesManager with the given key if exists, default value otherwise
     */
    @Suppress("UNCHECKED_CAST")
    fun <T> getPreference(key: String, defaultValue: T): T {
        val allPreferences = sharedPreferences.all

        return if (allPreferences.containsKey(key)) {
            allPreferences[key]
        } else {
            defaultValue
        } as T

    }
}
