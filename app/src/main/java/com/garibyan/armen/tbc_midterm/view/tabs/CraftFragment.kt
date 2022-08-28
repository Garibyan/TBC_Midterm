package com.garibyan.armen.tbc_midterm.view.tabs

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.garibyan.armen.tbc_midterm.R
import com.garibyan.armen.tbc_midterm.adapters.CraftIngredientAdapter
import com.garibyan.armen.tbc_midterm.databinding.FragmentCraftBinding
import com.garibyan.armen.tbc_midterm.network.Resource
import com.garibyan.armen.tbc_midterm.network.responcemodels.Ingredient
import com.garibyan.armen.tbc_midterm.utils.extentions.collectLatestFlow
import com.garibyan.armen.tbc_midterm.utils.extentions.toast
import com.garibyan.armen.tbc_midterm.view.BaseFragment
import com.garibyan.armen.tbc_midterm.viewmodel.tabs.CraftViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CraftFragment : BaseFragment<FragmentCraftBinding>(
    FragmentCraftBinding::inflate
) {

    private val craftIngredientAdapter: CraftIngredientAdapter by lazy { CraftIngredientAdapter() }
    private val viewModel: CraftViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getIngredients()
        observer()
        onClickListeners()
    }

    private fun initRecyclerView(ingredientsList: List<Ingredient>) = with(binding.recyclerView) {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = craftIngredientAdapter
        craftIngredientAdapter.submitList(ingredientsList)

    }

    private fun onClickListeners() = with(binding) {
        btnRetry.setOnClickListener {
            viewModel.getIngredients()
        }
    }

    private fun search(ingredientsList: List<Ingredient>) {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(text: String?): Boolean {
                val list = mutableListOf<Ingredient>()

                if (!text.isNullOrEmpty()) {
                    list.addAll(craftIngredientAdapter.currentList.filter {
                        it.item.lowercase().contains(text.lowercase())
                    })
                } else {
                    list.addAll(ingredientsList)
                }
                craftIngredientAdapter.submitList(list)
                return true
            }

            override fun onQueryTextSubmit(p0: String?): Boolean = true
        })
    }

    private fun observer() {
        collectLatestFlow(viewModel.ingredientsFlow) {
            when (it) {
                is Resource.Success -> {
                    successfulState()
                    initRecyclerView(it.value.drinks)
                    search(it.value.drinks)
                }
                is Resource.Error -> {
                    errorState(it.isNetworkError!!)
                }
                is Resource.Loading -> {
                    loadingState()
                }
            }
        }
    }

    private fun successfulState() = with(binding) {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        btnRetry.visibility = View.GONE
    }

    private fun errorState(isNetworkError: Boolean) = with(binding) {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.GONE
        btnRetry.visibility = View.VISIBLE
        if (isNetworkError) {
            requireContext().toast(getString(R.string.network_error))
        } else {
            requireContext().toast(getString(R.string.something_went_wrong))
        }
    }

    private fun loadingState() = with(binding) {
        binding.progressBar.visibility = View.VISIBLE
        btnRetry.visibility = View.GONE
        recyclerView.visibility = View.GONE
    }

}