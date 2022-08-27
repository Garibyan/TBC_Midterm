package com.garibyan.armen.tbc_midterm.view.tabs

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.garibyan.armen.tbc_midterm.R
import com.garibyan.armen.tbc_midterm.adapters.CategoriesAdapter
import com.garibyan.armen.tbc_midterm.databinding.FragmentCategoriesBinding
import com.garibyan.armen.tbc_midterm.extentions.collectLatestFlow
import com.garibyan.armen.tbc_midterm.extentions.toast
import com.garibyan.armen.tbc_midterm.network.Resource
import com.garibyan.armen.tbc_midterm.network.responcemodels.Category
import com.garibyan.armen.tbc_midterm.utils.HomeTabRequestTypes
import com.garibyan.armen.tbc_midterm.view.BaseFragment
import com.garibyan.armen.tbc_midterm.viewmodel.tabs.CategoriesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : BaseFragment<FragmentCategoriesBinding>(
    FragmentCategoriesBinding::inflate
) {

    private val viewModel: CategoriesViewModel by viewModels()
    private val categoriesAdapter: CategoriesAdapter by lazy { CategoriesAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCategories()
        observer()
        onClickListeners()
    }

    private fun onClickListeners() = with(binding) {
        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        categoriesAdapter.onItemClickListener = {
            findNavController().navigate(
                CategoriesFragmentDirections.actionCategoriesFragmentToCocktailsListFragment(
                    HomeTabRequestTypes.REQUEST_SEARCH_BY_CATEGORY, it
                )
            )
        }
        btnRetry.setOnClickListener {
            viewModel.getCategories()
        }
    }

    private fun initRecyclerView(categoriesList: List<Category>) = with(binding.rvCategories) {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = categoriesAdapter
        categoriesAdapter.submitList(categoriesList)
    }

    private fun observer() {
        collectLatestFlow(viewModel.categoryFlow) {
            when (it) {
                is Resource.Success -> {
                    Log.d("state", "Success")
                    successfulState()
                    initRecyclerView(it.value.drinks)
                }
                is Resource.Error -> {
                    errorState(it.isNetworkError!!)
                    Log.d("state", "Error")
                }
                is Resource.Loading -> {
                    loadingState()
                    Log.d("state", "Loading")
                }
            }
        }
    }

    private fun successfulState() = with(binding) {
        progressBar.visibility = View.GONE
        rvCategories.visibility = View.VISIBLE
        btnRetry.visibility = View.GONE
    }

    private fun errorState(isNetworkError: Boolean) = with(binding) {
        progressBar.visibility = View.GONE
        rvCategories.visibility = View.GONE
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
        rvCategories.visibility = View.GONE
    }


}