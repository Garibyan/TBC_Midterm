package com.garibyan.armen.tbc_midterm.viewmodel.auth

import androidx.lifecycle.ViewModel
import com.garibyan.armen.tbc_midterm.repository.DataStore
import com.garibyan.armen.tbc_midterm.utils.DataStoreKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(private val dataStore: DataStore) : ViewModel() {

    fun checkUserSession() = dataStore.getPreferences(DataStoreKeys.EMAIL_KEY)

}