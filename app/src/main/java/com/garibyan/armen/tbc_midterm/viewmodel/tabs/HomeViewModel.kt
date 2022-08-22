package com.garibyan.armen.tbc_midterm.viewmodel.tabs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.garibyan.armen.tbc_midterm.network.ApiClient
import com.garibyan.armen.tbc_midterm.network.Resource
import com.garibyan.armen.tbc_midterm.network.responcemodels.Cocktails
import com.garibyan.armen.tbc_midterm.repository.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: MainRepository = MainRepository(ApiClient.apiService)
) : ViewModel() {

    private val _stateFlow = MutableStateFlow<Resource<Cocktails>>(Resource.Loading)
    val stateFlow = _stateFlow.asStateFlow()


    fun getCocktailById(id: String){
        viewModelScope.launch {
            repository.getCocktailById(id).collect{
                _stateFlow.value = it
            }
        }
    }
}