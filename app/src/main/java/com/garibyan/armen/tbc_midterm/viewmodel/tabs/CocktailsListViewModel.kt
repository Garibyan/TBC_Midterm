package com.garibyan.armen.tbc_midterm.viewmodel.tabs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.garibyan.armen.tbc_midterm.network.Resource
import com.garibyan.armen.tbc_midterm.network.responcemodels.Cocktail
import com.garibyan.armen.tbc_midterm.network.responcemodels.Drinks
import com.garibyan.armen.tbc_midterm.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CocktailsListViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val _popularCocktails = MutableStateFlow<Resource<Drinks<Cocktail>>>(Resource.Loading)
    val popularCocktails = _popularCocktails.asStateFlow()

    fun getPopularCocktails(){
        viewModelScope.launch {
            repository.getPopularCocktails().collect{
                _popularCocktails.emit(it)
            }
        }
    }

    fun getLatestCocktails(){
        viewModelScope.launch {
            repository.getLatestCocktails().collect{
                _popularCocktails.emit(it)
            }
        }
    }

    fun getRandomTenCocktails(){
        viewModelScope.launch {
            repository.getRandomTenCocktails().collect{
                _popularCocktails.emit(it)
            }
        }
    }

    fun getCocktailsByCategory(category: String){
        viewModelScope.launch {
            repository.getCocktailsByCategory(category).collect{
                _popularCocktails.emit(it)
            }
        }
    }

    fun getCocktailsByIngredient(ingredient: String){
        viewModelScope.launch {
            repository.getCocktailsByIngredient(ingredient).collect{
                _popularCocktails.emit(it)
            }
        }
    }
}