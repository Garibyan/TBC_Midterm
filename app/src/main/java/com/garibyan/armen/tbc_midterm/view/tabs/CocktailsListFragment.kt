package com.garibyan.armen.tbc_midterm.view.tabs

import android.os.Bundle
import android.util.Log.d
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.garibyan.armen.tbc_midterm.adapters.CocktailsAdapter
import com.garibyan.armen.tbc_midterm.databinding.FragmentCocktailsListBinding
import com.garibyan.armen.tbc_midterm.utils.extentions.collectLatestFlow
import com.garibyan.armen.tbc_midterm.network.Resource
import com.garibyan.armen.tbc_midterm.network.responcemodels.Cocktail
import com.garibyan.armen.tbc_midterm.network.responcemodels.Drinks
import com.garibyan.armen.tbc_midterm.utils.DetailsTabRequestType
import com.garibyan.armen.tbc_midterm.utils.HomeTabRequestTypes
import com.garibyan.armen.tbc_midterm.view.BaseFragment
import com.garibyan.armen.tbc_midterm.viewmodel.tabs.CocktailsListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CocktailsListFragment : BaseFragment<FragmentCocktailsListBinding>(
    FragmentCocktailsListBinding::inflate
) {
    private val viewModel: CocktailsListViewModel by viewModels()
    private val args: CocktailsListFragmentArgs by navArgs()
    private val cocktailsAdapter: CocktailsAdapter by lazy { CocktailsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkRequestType(args.requestType)
        onClickListeners()
    }

    private fun onClickListeners() = with(binding) {
        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        cocktailsAdapter.onItemClickListener = {
            d("cocktailId", it.idDrink.toString())
            findNavController().navigate(
                CocktailsListFragmentDirections.actionCocktailsListFragmentToCocktailDetailsFragment(
                    it.idDrink.toString(),
                    DetailsTabRequestType.REQUEST_COCKTAIL_BY_ID
                )
            )
        }
        btnRetry.setOnClickListener {
            checkRequestType(args.requestType)
        }
    }

    private fun initRecyclerView(cocktailsList: List<Cocktail>) = with(binding.rvCocktails) {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = cocktailsAdapter
        d("mylog", cocktailsList.toString())
        cocktailsAdapter.submitList(cocktailsList)
    }

    private fun search(cocktailsList: List<Cocktail>) {
                binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                    androidx.appcompat.widget.SearchView.OnQueryTextListener {
                    override fun onQueryTextChange(text: String?): Boolean {
                        val list = mutableListOf<Cocktail>()

                        if (!text.isNullOrEmpty()) {
                            list.addAll(cocktailsAdapter.currentList.filter {
                                it.name.toString().lowercase().contains(text.lowercase())
                            })
                        } else {
                            list.addAll(cocktailsList)
                        }
                        cocktailsAdapter.submitList(list)
                        return true
                    }

            override fun onQueryTextSubmit(p0: String?): Boolean = true
        })
    }

    private fun checkRequestType(requestType: Int) {
        when (requestType) {
            HomeTabRequestTypes.REQUEST_POPULAR_COCKTAILS -> {
                viewModel.getPopularCocktails()
                collectLatestFlow(viewModel.popularCocktails) { checkState(it) }
            }
            HomeTabRequestTypes.REQUEST_LATEST_COCKTAILS -> {
                viewModel.getLatestCocktails()
                collectLatestFlow(viewModel.popularCocktails) { checkState(it) }
            }
            HomeTabRequestTypes.REQUEST_RANDOM_TEN_COCKTAILS -> {
                viewModel.getRandomTenCocktails()
                collectLatestFlow(viewModel.popularCocktails) { checkState(it) }
            }
            HomeTabRequestTypes.REQUEST_SEARCH_BY_CATEGORY -> {
                viewModel.getCocktailsByCategory(args.category!!)
                collectLatestFlow(viewModel.popularCocktails) { checkState(it) }
            }
            HomeTabRequestTypes.REQUEST_SEARCH_BY_INGREDIENT -> {
                viewModel.getCocktailsByIngredient(args.category!!)
                collectLatestFlow(viewModel.popularCocktails) { checkState(it) }
            }
        }
    }

    private fun checkState(resource: Resource<Drinks<Cocktail>>) {
        when (resource) {
            is Resource.Success -> {
                d("state", "Success")
                successfulState()
                initRecyclerView(resource.value.drinks)
                search(resource.value.drinks)
            }
            is Resource.Error -> {
                errorState(resource.isNetworkError!!)
                d("state", "Error")
            }
            is Resource.Loading -> {
                loadingState()
                d("state", "Loading")
            }
        }
    }

    private fun successfulState() = with(binding) {
        progressBar.visibility = View.GONE
        rvCocktails.visibility = View.VISIBLE
        btnRetry.visibility = View.GONE
    }

    private fun errorState(isNetworkError: Boolean) = with(binding) {
        progressBar.visibility = View.GONE
        rvCocktails.visibility = View.GONE
        btnRetry.visibility = View.VISIBLE
        if (isNetworkError) {
            Toast.makeText(requireContext(), "Network error", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadingState() = with(binding) {
        binding.progressBar.visibility = View.VISIBLE
        btnRetry.visibility = View.GONE
        rvCocktails.visibility = View.GONE
    }
}