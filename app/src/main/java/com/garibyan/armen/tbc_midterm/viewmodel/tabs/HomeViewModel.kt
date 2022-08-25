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

    private val _popularCocktails = MutableStateFlow<Resource<Cocktails>>(Resource.Loading)
    val popularCocktails = _popularCocktails.asStateFlow()

    private val _latestCocktails = MutableStateFlow<Resource<Cocktails>>(Resource.Loading)
    val latestCocktails = _latestCocktails.asStateFlow()

    fun getPopularCocktails(){
        viewModelScope.launch {
            repository.getPopularCocktails().collect{
                _popularCocktails.value = it
            }
        }
    }

    fun getLatestCocktails(){
        viewModelScope.launch {
            repository.getLatestCocktails().collect{
                _latestCocktails.value = it
            }
        }
    }
}