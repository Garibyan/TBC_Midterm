package com.garibyan.armen.tbc_midterm.viewmodel.tabs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.garibyan.armen.tbc_midterm.network.Resource
import com.garibyan.armen.tbc_midterm.network.responcemodels.Drinks
import com.garibyan.armen.tbc_midterm.network.responcemodels.Ingredient
import com.garibyan.armen.tbc_midterm.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CraftViewModel @Inject constructor(private val repository: MainRepository): ViewModel() {

    private val _ingredientsFlow = MutableStateFlow<Resource<Drinks<Ingredient>>>(Resource.Loading)
    val  ingredientsFlow = _ingredientsFlow.asStateFlow()

    fun getIngredients() {
        viewModelScope.launch {
            repository.getAllIngredients().collectLatest {
                _ingredientsFlow.value = it
            }
        }

    }
}