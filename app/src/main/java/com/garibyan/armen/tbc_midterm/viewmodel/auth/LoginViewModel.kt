package com.garibyan.armen.tbc_midterm.viewmodel.auth

import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.garibyan.armen.tbc_midterm.repository.DataStore
import com.garibyan.armen.tbc_midterm.utils.DataStoreKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val dataStore: DataStore) : ViewModel() {

    fun saveEmail(email: String){
        viewModelScope.launch {
            d("aslbn", email)
            dataStore.save(DataStoreKeys.EMAIL_KEY, email)
        }
    }
}