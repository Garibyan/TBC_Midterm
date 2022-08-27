package com.garibyan.armen.tbc_midterm.viewmodel.tabs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.garibyan.armen.tbc_midterm.network.Resource
import com.garibyan.armen.tbc_midterm.network.responcemodels.Cocktail
import com.garibyan.armen.tbc_midterm.network.responcemodels.Drinks
import com.garibyan.armen.tbc_midterm.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CocktailDetailsViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {


    private val _cocktailFlow = MutableStateFlow<Resource<Drinks<Cocktail>>>(Resource.Loading)
    val cocktailFlow = _cocktailFlow.asStateFlow()

    fun getCocktailById(cocktailId: String){
        viewModelScope.launch {
            repository.getCocktailById(cocktailId).collect{
                _cocktailFlow.value = it
            }
        }
    }

    fun getRandomCocktail(){
        viewModelScope.launch {
            repository.getRandomCocktails().collect{
                _cocktailFlow.value = it
            }
        }
    }


}