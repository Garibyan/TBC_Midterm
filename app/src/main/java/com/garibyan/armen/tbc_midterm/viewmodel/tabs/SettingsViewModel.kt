package com.garibyan.armen.tbc_midterm.viewmodel.tabs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.garibyan.armen.tbc_midterm.repository.DataStore
import com.garibyan.armen.tbc_midterm.utils.DataStoreKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val dataStore: DataStore) : ViewModel() {

    fun getSavedEmail() = dataStore.getPreferences(DataStoreKeys.EMAIL_KEY)

    fun clear(){
        viewModelScope.launch {
            dataStore.clear()
        }
    }

}