package com.garibyan.armen.tbc_midterm.viewmodel.tabs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.garibyan.armen.tbc_midterm.network.Resource
import com.garibyan.armen.tbc_midterm.network.responcemodels.Category
import com.garibyan.armen.tbc_midterm.network.responcemodels.Drinks
import com.garibyan.armen.tbc_midterm.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    private val _categoryFlow = MutableSharedFlow<Resource<Drinks<Category>>>()
    val categoryFlow = _categoryFlow.asSharedFlow()


    fun getCategories(){
        viewModelScope.launch {
            repository.getAllCategories().collectLatest{
                _categoryFlow.emit(it)
            }
        }
    }

}