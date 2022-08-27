package com.garibyan.armen.tbc_midterm.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val PREFERENCE_NAME = "PREFERENCE_NAME"

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(PREFERENCE_NAME)
class DataStore(private val context: Context) {

    fun getPreferences(key: String): Flow<String> = context.dataStore.data
        .map {
            it[stringPreferencesKey(key)] ?: ""
        }

    suspend fun save(key: String, value: String) {
        context.dataStore.edit {
            it[stringPreferencesKey(key)] = value
        }
    }

    suspend fun clear() {
        context.dataStore.edit {
            it.clear()
        }
    }
}